package com.wildcodeschool.festivalorleansjoue.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Referent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "society_id")
	private Society society;
	@ManyToOne
	@JoinColumn(name = "society_referent")
	private SocietyReferent societyReferent;
	

	public Referent() {

	}
	

	public Long getId() {

		return this.id;
	}
	

	public void setId(Long id) {

		this.id = id;
	}
	

	public String getFirstName() {

		return this.firstName;
	}
	

	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}
	

	public String getLastName() {

		return this.lastName;
	}
	

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}
	

	public Society getSociety() {

		return this.society;
	}
	

	public void setSociety(Society society) {

		this.society = society;
	}

}
