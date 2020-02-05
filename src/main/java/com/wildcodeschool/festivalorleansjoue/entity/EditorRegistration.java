package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class EditorRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "society_id")
	private Society society;
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	@ManyToMany //(mappedBy = "registrations")
	@JoinTable(name = "game_registration", joinColumns = @JoinColumn(name = "editor_registration_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "agent_registration", joinColumns = @JoinColumn(name = "editor_registration_id"), inverseJoinColumns = @JoinColumn(name = "agent_id"))
	private List<Agent> agents = new ArrayList<>();
	private int tablesQuantity;
	private boolean electricalSupplyNeed;
	private boolean saleOption;
	private boolean volunteersNeed;
	@Column(name="volunteerCount")
	private Double volunteerCount = 0.0d;
	@Lob
	private String comments;
	private double registrationCost;
	private String state;//pending, canceled, validated or rejected
	private Date subscriptionDate;
	private boolean agentProvided;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	
	public EditorRegistration() {

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
	

	public List<Game> getGames() {
		
		return this.games;
	}


	public void setGames(List<Game> games) {
		
		this.games = games;
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
	

	public boolean isSaleOption() {
		
		return this.saleOption;
	}
	

	public void setSaleOption(boolean saleOption) {
		
		this.saleOption = saleOption;
	}


	public boolean isVolunteersNeed() {
		
		return this.volunteersNeed;
	}


	public void setVolunteersNeed(boolean volunteersNeed) {
		
		this.volunteersNeed = volunteersNeed;
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


	public Invoice getInvoice() {
		return invoice;
	}


	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


	public Double getVolunteerCount() {
		return volunteerCount;
	}


	public void setVolunteerCount(Double volunteerCount) {
		this.volunteerCount = volunteerCount;
	}
	
	public void updateVolunteerCount() {
		if (this.volunteersNeed) {
			if (this.agents == null || this.agents.size() == 0)
				this.volunteerCount = (double) this.tablesQuantity / 2; 
			else
				this.volunteerCount = (double) (this.tablesQuantity - this.agents.size() * 2);			
		} else {
			this.volunteerCount = 0d;
		}
		
		if (this.volunteerCount < 0)
			this.volunteerCount = 0d;
	}	
}
