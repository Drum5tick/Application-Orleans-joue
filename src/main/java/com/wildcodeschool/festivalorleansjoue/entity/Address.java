package com.wildcodeschool.festivalorleansjoue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String wording;
	private String phoneNumber1;
	private String phoneNumber2;
	private String remarks;
	private String address1;
	private String address2;
	private String address3;
	private String postalCode;
	private String city;
	private String contactEmail;
	@OneToOne(mappedBy = "address")
	private User user;
	@OneToOne(mappedBy = "address")
	private Society society;
	

	public Address() {

	}

	public Long getId() {

		return this.id;
	}
	

	public void setId(Long id) {

		this.id = id;
	}
	

	public String getWording() {

		return this.wording;
	}
	

	public void setWording(String wording) {

		this.wording = wording;
	}
	

	public String getPhoneNumber1() {

		return this.phoneNumber1;
	}
	

	public void setPhoneNumber1(String phoneNumber1) {

		this.phoneNumber1 = phoneNumber1;
	}
	

	public String getPhoneNumber2() {

		return this.phoneNumber2;
	}
	

	public void setPhoneNumber2(String phoneNumber2) {

		this.phoneNumber2 = phoneNumber2;
	}
	

	public String getRemarks() {

		return this.remarks;
	}
	

	public void setRemarks(String remarks) {

		this.remarks = remarks;
	}
	

	public String getAddress1() {

		return this.address1;
	}
	

	public void setAddress1(String adress1) {

		this.address1 = adress1;
	}
	

	public String getAddress2() {

		return this.address2;
	}
	

	public void setAddress2(String adress2) {

		this.address2 = adress2;
	}
	

	public String getAddress3() {

		return this.address3;
	}
	

	public void setAddress3(String adress3) {

		this.address3 = adress3;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}
}
