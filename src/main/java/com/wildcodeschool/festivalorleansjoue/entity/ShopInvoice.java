package com.wildcodeschool.festivalorleansjoue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ShopInvoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "ShopRegistration_id")
	private ShopRegistration shopRegistration;
	private boolean factureSend;
	private String uploadFilePdf;

	public ShopInvoice() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShopRegistration getShopRegistration() {
		return shopRegistration;
	}

	public void setShopRegistration(ShopRegistration shopRegistration) {
		this.shopRegistration = shopRegistration;
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
