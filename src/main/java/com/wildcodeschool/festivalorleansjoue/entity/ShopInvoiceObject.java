package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.Date;

public class ShopInvoiceObject {

	private Long factOrder;
	private Date date;
	private String societyName;
	private String address;
	private String postalCode;
	private String city;
	private String contactEmail;
	private Long siret;
	private String tva;
	private String eventName;
	private Date eventDate;
	private float total;
	private String factureMessage;
	private boolean factureSend;
	private String uploadFilePdf;
	private ArrayList<ShopInvoiceDetail> invoiceDetails;

	public ShopInvoiceObject() {

	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getFactOrder() {
		return factOrder;
	}

	public void setFactOrder(Long factOrder) {
		this.factOrder = factOrder;
	}

	public String getSocietyName() {
		return this.societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Long getSiret() {
		return siret;
	}

	public void setSiret(Long siret) {
		this.siret = siret;
	}

	public String getTva() {
		return tva;
	}

	public void setTva(String tva) {
		this.tva = tva;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public ArrayList<ShopInvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(ArrayList<ShopInvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public String getFactureMessage() {
		return this.factureMessage;
	}

	public void setFactureMessage(String factureMessage) {
		this.factureMessage = factureMessage;
	}

	public boolean isFactureSend() {
		return factureSend;
	}

	public void setFactureSend(boolean factureSend) {
		this.factureSend = factureSend;
	}

	public String getUploadFilePdf() {
		return uploadFilePdf;
	}

	public void setUploadFilePdf(String uploadFilePdf) {
		this.uploadFilePdf = uploadFilePdf;
	}

}
