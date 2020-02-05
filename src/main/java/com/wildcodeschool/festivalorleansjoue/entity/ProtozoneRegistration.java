package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProtozoneRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	@Lob
	private String comments;
	private String state;
	private Date subscriptionDate;
	
	
	public ProtozoneRegistration() {

	}


	public Long getId() {
		
		return this.id;
	}


	public void setId(Long id) {
		
		this.id = id;
	}


	public Event getEvent() {
		
		return this.event;
	}
	

	public void setEvent(Event event) {
		
		this.event = event;
	}
	


	public User getUser() {
		
		return this.user;
	}


	public void setUser(User user) {
		
		this.user = user;
	}


	public String getComments() {
		
		return this.comments;
	}


	public void setComments(String comments) {
		
		this.comments = comments;
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

}
