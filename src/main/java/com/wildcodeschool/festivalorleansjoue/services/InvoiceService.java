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
import com.wildcodeschool.festivalorleansjoue.repository.InvoiceRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.utils.PdfGeneratorUtil;

@Service
public class InvoiceService {

	@Autowired
	PdfGeneratorUtil pdfGeneratorUtil;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	EditorRegistrationService registrationService;
	
	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;

	@Autowired
	FileService fileService;

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	private String baseDir;
	
	public InvoiceObject sendFacture(Long editorRegId) {
		
		EditorRegistration editorRegistration = registrationService.findEditorRegById(editorRegId);
		InvoiceObject invoice = new InvoiceObject();
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		InvoiceDetail invoiceDetail2 = new InvoiceDetail();
		InvoiceDetail invoiceDetailDiscount = new InvoiceDetail();
		ArrayList<InvoiceDetail> tempInvoiceDetails = new ArrayList<InvoiceDetail>();
		/* set de la facture */
		invoice.setDate(new Date());
		if (invoiceRepository.findTopByOrderByIdDesc() == null) {
			invoice.setFactOrder(1l);
		} else
			invoice.setFactOrder(invoiceRepository.findTopByOrderByIdDesc().getId() + 1l);
		invoice.setSocietyName(editorRegistration.getSociety().getName());
		invoice.setAddress(editorRegistration.getSociety().getAddress().getAddress1());
		invoice.setPostalCode(editorRegistration.getSociety().getAddress().getPostalCode());
		invoice.setCity(editorRegistration.getSociety().getAddress().getCity());
		invoice.setContactEmail(editorRegistration.getSociety().getAddress().getContactEmail());
		invoice.setSiret(editorRegistration.getSociety().getSiret());
		invoice.setTva(editorRegistration.getSociety().getTva());
		invoice.setEventName(editorRegistration.getEvent().getName());
		invoice.setEventDate(editorRegistration.getEvent().getEventBeginningDate());
		invoice.setTotal((editorRegistration.getTablesQuantity() * editorRegistration.getEvent().getPricePerTable())
				+ editorRegistration.getEvent().getSaleOptionPrice());

		/* set des détails */
		invoiceDetail.setDescription("Tables mises à disposition");
		invoiceDetail.setPrice(editorRegistration.getEvent().getPricePerTable());
		invoiceDetail.setQuantity(editorRegistration.getTablesQuantity());
		invoiceDetail.setTotal(invoiceDetail.getPrice() * invoiceDetail.getQuantity());
		tempInvoiceDetails.add(invoiceDetail);
		
		if (invoiceDetail.getQuantity()>editorRegistration.getEvent().getDiscount()){
			invoiceDetailDiscount.setDescription("Remise " + editorRegistration.getEvent().getDiscount() +" tables achetée(s), 1 offerte");
			invoiceDetailDiscount.setPrice(invoiceDetail.getPrice() *(-1) );
			invoiceDetailDiscount.setQuantity(invoiceDetail.getQuantity() / editorRegistration.getEvent().getDiscount());
			invoiceDetailDiscount.setTotal(invoiceDetailDiscount.getPrice() * invoiceDetailDiscount.getQuantity());
			tempInvoiceDetails.add(invoiceDetailDiscount);
		}
		if (editorRegistration.isSaleOption() == true) {
			invoiceDetail2.setDescription("Option vente sur stand");
			invoiceDetail2.setPrice(editorRegistration.getEvent().getSaleOptionPrice());
			invoiceDetail2.setQuantity(1);
			invoiceDetail2.setTotal(invoiceDetail2.getPrice() * invoiceDetail2.getQuantity());
			tempInvoiceDetails.add(invoiceDetail2);
		}

		invoice.setInvoiceDetails(tempInvoiceDetails);

		invoice.setTotal((editorRegistration.getTablesQuantity() * editorRegistration.getEvent().getPricePerTable())
				+ editorRegistration.getEvent().getSaleOptionPrice());

		return invoice;
	}
	
	public String generatePdfInvoice (InvoiceObject invoiceObject, Long id) throws Exception {
		
		Map<String,Object> data = new HashMap<String,Object>();
	    data.put("facture",invoiceObject);
	    String societyName = registrationService.findEditorRegById(id).getSociety().getName().replace(' ', '_');
	    String myInvoice = pdfGeneratorUtil.createPdf("invoice",data, societyName); 

	    return myInvoice;
	}

	
	public void saveInvoice(Invoice invoice) {

		invoiceRepository.save(invoice);
	}

	public void deleteInvoice(Long id) {

		EditorRegistration registration = registrationRepository.findByInvoice(invoiceRepository.getOne(id));
		fileService.deleteFile(invoiceRepository.getOne(id).getUploadFilePdf());
		registration.setInvoice(null);
		registrationRepository.save(registration);
		invoiceRepository.deleteById(id);
	}

	public void downloadInvoice(Long invoiceId, HttpServletRequest request, HttpServletResponse response) {

		Invoice invoice = invoiceRepository.getOne(invoiceId);
		String dataDirectory = invoice.getUploadFilePdf();
		Path file = Paths.get(baseDir + dataDirectory);
		
		if (Files.exists(file)) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=facture" + invoiceId + ".pdf");

			try {
				Files.copy(file, response.getOutputStream());

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
