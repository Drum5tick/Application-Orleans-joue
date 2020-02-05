package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String firstname;
	String lastname;
	String email;
	String phone;
	boolean gardenParty;
	boolean accommodation;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "agents")
	private List<EditorRegistration> editorRegistrations = new ArrayList<>();
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "agents")
	private List<ShopRegistration> shopRegistrations = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "society_id")
	private Society society;
	
		
	public Agent() {
	}


	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstname() {
		return this.firstname;
	}


	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}


	public String getLastname() {
		return this.lastname;
	}


	public void setLastname(String lastName) {
		this.lastname = lastName;
	}


	public String getEmail() {
		return this.email;
	}


	public void setEmail(String mail) {
		this.email = mail;
	}


	public String getPhone() {
		return this.phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public boolean isGardenParty() {
		return this.gardenParty;
	}


	public void setGardenParty(boolean gardenParty) {
		this.gardenParty = gardenParty;
	}


	public boolean isAccommodation() {
		return this.accommodation;
	}


	public void setAccommodation(boolean accommodation) {
		this.accommodation = accommodation;
	}


	public List<EditorRegistration> getRegistrations() {
		return editorRegistrations;
	}


	public void setRegistration(List<EditorRegistration> editorRegistrations) {
		this.editorRegistrations = editorRegistrations;
	}


	public List<ShopRegistration> getShopRegistrations() {
		return shopRegistrations;
	}


	public void setShopRegistrations(List<ShopRegistration> shopRegistrations) {
		this.shopRegistrations = shopRegistrations;
	}


	public Society getSociety() {
		return this.society;
	}


	public void setSociety(Society society) {
		this.society = society;
	}
}
