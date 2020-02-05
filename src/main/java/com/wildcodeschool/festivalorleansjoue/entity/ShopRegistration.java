package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class ShopRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "society_id")
	private Society society;
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	@ManyToMany
	@JoinTable(name = "agent_shop_registration", joinColumns = @JoinColumn(name = "shop_registration_id"), inverseJoinColumns = @JoinColumn(name = "agent_id"))
	private List<Agent> agents = new ArrayList<>();
	private int tablesQuantity;
	private boolean electricalSupplyNeed;
	@Lob
	private String comments;
	private double registrationCost;
	private String state;
	private Date subscriptionDate;
	private boolean agentProvided;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "shop_invoice_id")
	private ShopInvoice shopInvoice;
	
	public ShopRegistration() {
		super();
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Society getSociety() {
		return this.society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Agent> getAgents() {
		return this.agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public int getTablesQuantity() {
		return this.tablesQuantity;
	}

	public void setTablesQuantity(int tablesQuantity) {
		this.tablesQuantity = tablesQuantity;
	}

	public boolean isElectricalSupplyNeed() {
		return this.electricalSupplyNeed;
	}

	public void setElectricalSupplyNeed(boolean electricalSupplyNeed) {
		this.electricalSupplyNeed = electricalSupplyNeed;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getRegistrationCost() {
		return this.registrationCost;
	}

	public void setRegistrationCost(double registrationCost) {
		this.registrationCost = registrationCost;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getSubscriptionDate() {
		return this.subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public boolean isAgentProvided() {
		return this.agentProvided;
	}

	public void setAgentProvided(boolean agentProvided) {
		this.agentProvided = agentProvided;
	}


	public ShopInvoice getShopInvoice() {
		return shopInvoice;
	}


	public void setShopInvoice(ShopInvoice shopInvoice) {
		this.shopInvoice = shopInvoice;
	}
	

}
