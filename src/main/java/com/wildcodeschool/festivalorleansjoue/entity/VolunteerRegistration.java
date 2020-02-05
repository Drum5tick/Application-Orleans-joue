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
public class VolunteerRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	@ManyToMany
	@JoinTable(name = "VolunteerRegistrationTasks", joinColumns = @JoinColumn(name = "volunteer_registration_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
	private List<Tasks> chosenTasks = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "VolunteerRegistrationTimeSlot", joinColumns = @JoinColumn(name = "volunteer_registration_id"), inverseJoinColumns = @JoinColumn(name = "timeSlot_id"))
	private List<TimeSlot> chosenTimeSlot = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "volunteer_registration_planning", joinColumns = @JoinColumn(name = "volunteer_registration_id"), inverseJoinColumns = @JoinColumn(name = "planning_id"))
	private List<Planning> planning = new ArrayList<>();
	@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	@Lob
	private String comments;
	private String state;
	private Date subscriptionDate;
	
	
	public VolunteerRegistration() {

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
	

	public List<Tasks> getChosenTasks() {
		
		return this.chosenTasks;
	}


	public void setChosenTasks(List<Tasks> chosenTasks) {
		
		this.chosenTasks = chosenTasks;
	}


	public List<TimeSlot> getChosenTimeSlot() {
		return chosenTimeSlot;
	}


	public void setChosenTimeSlot(List<TimeSlot> chosenTimeSlot) {
		this.chosenTimeSlot = chosenTimeSlot;
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


	public List<Planning> getPlanning() {
		return planning;
	}


	public void setPlanning(List<Planning> planning) {
		this.planning = planning;
	}

}
