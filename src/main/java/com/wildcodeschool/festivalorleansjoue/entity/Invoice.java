package com.wildcodeschool.festivalorleansjoue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "editorRegistration_id")
	private EditorRegistration editorRegistration;
	private boolean factureSend;
	private String uploadFilePdf;

	public Invoice() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EditorRegistration getEditorRegistration() {
		return editorRegistration;
	}

	public void setEditorRegistration(EditorRegistration editorRegistration) {
		this.editorRegistration = editorRegistration;
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
