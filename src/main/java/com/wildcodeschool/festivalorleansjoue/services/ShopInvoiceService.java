package com.wildcodeschool.festivalorleansjoue.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Invoice;
import com.wildcodeschool.festivalorleansjoue.entity.InvoiceDetail;
import com.wildcodeschool.festivalorleansjoue.entity.InvoiceObject;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoiceDetail;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoiceObject;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.InvoiceRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ShopInvoiceRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ShopRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.utils.PdfGeneratorUtil;

@Service
public class ShopInvoiceService {

	@Autowired
	PdfGeneratorUtil pdfGeneratorUtil;

	@Autowired
	ShopInvoiceRepository shopInvoiceRepository;

	@Autowired
	EditorRegistrationService registrationService;

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@Autowired
	FileService fileService;

	@Autowired
	ShopRegistrationRepository shopRegistrationRepository;

	@Autowired
	private String baseDir;
	
	public ShopInvoiceObject sendFacture(Long shopRegId) {

		ShopRegistration shopRegistration = registrationService.findShopById(shopRegId);
		ShopInvoiceObject invoice = new ShopInvoiceObject();
		ShopInvoiceDetail invoiceDetail = new ShopInvoiceDetail();
		ArrayList<ShopInvoiceDetail> tempInvoiceDetails = new ArrayList<ShopInvoiceDetail>();
		/* set de la facture */
		invoice.setDate(new Date());
		if (shopInvoiceRepository.findTopByOrderByIdDesc() == null) {
			invoice.setFactOrder(1l);
		} else
			invoice.setFactOrder(shopInvoiceRepository.findTopByOrderByIdDesc().getId() + 1l);
		invoice.setSocietyName(shopRegistration.getSociety().getName());
		invoice.setAddress(shopRegistration.getSociety().getAddress().getAddress1());
		invoice.setPostalCode(shopRegistration.getSociety().getAddress().getPostalCode());
		invoice.setCity(shopRegistration.getSociety().getAddress().getCity());
		invoice.setContactEmail(shopRegistration.getSociety().getAddress().getContactEmail());
		invoice.setSiret(shopRegistration.getSociety().getSiret());
		invoice.setTva(shopRegistration.getSociety().getTva());
		invoice.setEventName(shopRegistration.getEvent().getName());
		invoice.setEventDate(shopRegistration.getEvent().getEventBeginningDate());
		invoice.setTotal((shopRegistration.getTablesQuantity() * shopRegistration.getEvent().getPricePerTable())
				+ shopRegistration.getEvent().getSaleOptionPrice());

		/* set des détails */
		invoiceDetail.setDescription("Inscription à l'évenement : " + shopRegistration.getEvent().getName());
		invoiceDetail.setPrice(shopRegistration.getEvent().getShopPrice());
		invoiceDetail.setQuantity(1);
		invoiceDetail.setTotal(invoiceDetail.getPrice() * invoiceDetail.getQuantity());
		tempInvoiceDetails.add(invoiceDetail);

		invoice.setInvoiceDetails(tempInvoiceDetails);

		invoice.setTotal(shopRegistration.getEvent().getShopPrice());

		return invoice;
	}

	public String generatePdfInvoice(ShopInvoiceObject invoiceObject, Long id) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("facture", invoiceObject);
		String societyName = registrationService.findShopById(id).getSociety().getName().replace(' ', '_');
		String myInvoice = pdfGeneratorUtil.createPdf("invoice", data, societyName);
		return myInvoice;

	}

	public void saveInvoice(ShopInvoice invoice) {

		shopInvoiceRepository.save(invoice);
	}

	public void deleteInvoice(Long id) {

		ShopRegistration registration = shopRegistrationRepository.findByShopInvoice(shopInvoiceRepository.getOne(id));
		fileService.deleteFile(shopInvoiceRepository.getOne(id).getUploadFilePdf());
		registration.setShopInvoice(null);
		shopRegistrationRepository.save(registration);
		shopInvoiceRepository.deleteById(id);
	}

	public void downloadInvoice(Long invoiceId, HttpServletRequest request, HttpServletResponse response) {

		ShopInvoice invoice = shopInvoiceRepository.getOne(invoiceId);
		String dataDirectory = invoice.getUploadFilePdf();
		Path file = Paths.get(baseDir + dataDirectory);

		if (Files.exists(file)) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=factureShop" + invoiceId + ".pdf");

			try {
				Files.copy(file, response.getOutputStream());

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
