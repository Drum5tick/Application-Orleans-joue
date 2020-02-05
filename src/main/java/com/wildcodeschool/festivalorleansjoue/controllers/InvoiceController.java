package com.wildcodeschool.festivalorleansjoue.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcodeschool.festivalorleansjoue.entity.Invoice;
import com.wildcodeschool.festivalorleansjoue.entity.InvoiceDetail;
import com.wildcodeschool.festivalorleansjoue.entity.InvoiceObject;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoiceDetail;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoiceObject;
import com.wildcodeschool.festivalorleansjoue.services.InvoiceService;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.ShopInvoiceService;
import com.wildcodeschool.festivalorleansjoue.utils.PdfGeneratorUtil;

@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	ShopInvoiceService shopInvoiceService;
	
	@Autowired
	EditorRegistrationService registrationService;

	PdfGeneratorUtil PdfGeneratorUtil;

	
	/* facture editeurs */
	@RequestMapping("admin/facture")
	public String showInvoiceHtml(@RequestParam Long editorRegId, Model model) {

		model.addAttribute("facture", invoiceService.sendFacture(editorRegId));
		model.addAttribute("regId", editorRegId);
		return "invoice";
	}

	@RequestMapping(value = "admin/facturePdf", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView showInvoiceHtml(@RequestBody String details) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			JsonNode root = objectMapper.readTree(details);
			InvoiceDetail[] invoicedetails = objectMapper.convertValue(root.get("details"), InvoiceDetail[].class);
			Long registrationId = Long.parseLong(root.get("editorRegId").asText());
			InvoiceObject invoiceObject = invoiceService.sendFacture(registrationId);
			String myInvoiceLink = "";
			ArrayList<InvoiceDetail> updatedDetails = new ArrayList<InvoiceDetail>();
			float total = 0;
			
			for (int i = 0; i < invoicedetails.length; i++) {
				updatedDetails.add(invoicedetails[i]);
				total+=invoicedetails[i].getTotal();
			}
			invoiceObject.setInvoiceDetails(updatedDetails);
			invoiceObject.setTotal(total);
			
			try {
				myInvoiceLink = invoiceService.generatePdfInvoice(invoiceObject, registrationId);
				Invoice invoice = new Invoice();
				invoice.setEditorRegistration(registrationService.findEditorRegById(registrationId));
				invoice.setFactureSend(true);
				invoice.setUploadFilePdf(myInvoiceLink);
				invoiceService.saveInvoice(invoice);
				registrationService.saveEditorRegWithInvoice(invoice);
			} catch (Exception e) {

				return new ModelAndView("redirect:/adminError");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getCause() + e.getMessage());
		}

		return new ModelAndView("redirect:/admin/gestionDesInscriptions");

	}

	@RequestMapping("admin/downloadInvoice")
	public void downloadInvoice(@RequestParam Long invoiceId, HttpServletRequest request, 
            HttpServletResponse response) {

		invoiceService.downloadInvoice(invoiceId, request, response);
	}
	
	@RequestMapping("admin/deleteInvoice")
	public ModelAndView deleteInvoice(@RequestParam Long invoiceId) {

		invoiceService.deleteInvoice(invoiceId);
		return new ModelAndView("redirect:/admin/gestionDesInscriptions");

	}

	
	/* facture shop */
	
	@RequestMapping("admin/factureShop")
	public String showShopInvoiceHtml(@RequestParam Long shopRegId, Model model) {

		model.addAttribute("facture", shopInvoiceService.sendFacture(shopRegId));
		model.addAttribute("shopRegId", shopRegId);
		return "shopInvoice";
	}

	@RequestMapping(value = "admin/facturePdfShop", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ModelAndView showShopInvoiceHtml(@RequestBody String details) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			JsonNode root = objectMapper.readTree(details);
			ShopInvoiceDetail[] invoicedetails = objectMapper.convertValue(root.get("details"), ShopInvoiceDetail[].class);
			Long registrationId = Long.parseLong(root.get("shopRegId").asText());
			ShopInvoiceObject shopInvoiceObject = shopInvoiceService.sendFacture(registrationId);
			String myInvoiceLink = "";
			ArrayList<ShopInvoiceDetail> updatedDetails = new ArrayList<ShopInvoiceDetail>();
			float total = 0;
			
			for (int i = 0; i < invoicedetails.length; i++) {
				updatedDetails.add(invoicedetails[i]);
				total+=invoicedetails[i].getTotal();
			}
			shopInvoiceObject.setInvoiceDetails(updatedDetails);
			shopInvoiceObject.setTotal(total);
			
			try {
				myInvoiceLink = shopInvoiceService.generatePdfInvoice(shopInvoiceObject, registrationId);
				ShopInvoice invoice = new ShopInvoice();
				invoice.setShopRegistration(registrationService.findShopById(registrationId));
				invoice.setFactureSend(true);
				invoice.setUploadFilePdf(myInvoiceLink);
				shopInvoiceService.saveInvoice(invoice);
				registrationService.saveShopRegWithInvoice(invoice);
			} catch (Exception e) {

				return new ModelAndView("redirect:/adminError");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getCause() + e.getMessage());
		}

		return new ModelAndView("redirect:/admin/gestionDesInscriptions/boutiques");

	}

	@RequestMapping("admin/downloadInvoiceShop")
	public void downloadShopInvoice(@RequestParam Long invoiceId, HttpServletRequest request, 
            HttpServletResponse response) {

		shopInvoiceService.downloadInvoice(invoiceId, request, response);
	}
	
	@RequestMapping("admin/deleteInvoiceShop")
	public ModelAndView deleteShopInvoice(@RequestParam Long invoiceId) {

		shopInvoiceService.deleteInvoice(invoiceId);
		return new ModelAndView("redirect:/admin/gestionDesInscriptions/boutiques");
		
	}
}
