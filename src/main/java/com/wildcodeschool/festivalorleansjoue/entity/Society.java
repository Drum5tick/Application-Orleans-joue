package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Society {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long siret;
	private String tva;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	@OneToMany(mappedBy = "society", cascade = CascadeType.ALL)
	private List<Referent> referents = new ArrayList<Referent>();
	@OneToMany(mappedBy = "society", cascade = CascadeType.ALL)
	private List<Game> game = new ArrayList<Game>();
	@OneToMany(mappedBy = "society", cascade = CascadeType.ALL)
	private List<EditorRegistration> regList = new ArrayList<>();
	@OneToMany(mappedBy = "society", cascade = CascadeType.ALL)
	private List<Agent> agents = new ArrayList<Agent>();
	@OneToMany(mappedBy = "society", cascade = CascadeType.ALL)
	private List<ShopRegistration> shopRegList = new ArrayList<>();


	public Society() {

	}
	

	public Long getId() {

		return this.id;
	}
	

	public void setId(Long id) {

		this.id = id;
	}
	

	public String getName() {

		return this.name;
	}
	

	public void setName(String name) {

		this.name = name;
	}
	

	public Long getSiret() {
		
		return this.siret;
	}


	public void setSiret(Long siret) {
		
		this.siret = siret;
	}


	public String getTva() {
		
		return this.tva;
	}


	public void setTva(String tva) {
		
		this.tva = tva;
	}


	public List<Referent> getReferents() {

		return this.referents;
	}
	

	public void setReferents(List<Referent> referents) {

		this.referents = referents;
	}
	

	public Address getAddress() {

		return this.address;
	}
	

	public void setAddress(Address address) {

		this.address = address;
	}


	public List<EditorRegistration> getRegList() {
		
		return regList;
	}


	public void setRegList(List<EditorRegistration> regList) {
		
		this.regList = regList;
	}


	public List<Game> getGame() {
		
		return this.game;
	}


	public void setGame(List<Game> game) {
		
		this.game = game;
	}


	public List<Agent> getAgents() {
		return this.agents;
	}


	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}


	public List<ShopRegistration> getShopRegList() {
		return this.shopRegList;
	}


	public void setShopRegList(List<ShopRegistration> shopRegList) {
		this.shopRegList = shopRegList;
	}	

}
