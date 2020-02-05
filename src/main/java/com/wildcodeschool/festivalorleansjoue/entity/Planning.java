package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Planning {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taskName;
	private String timeSlotName;
	private String timeSlotDescription;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "planning")
	private List<VolunteerRegistration> volunteerRegistration;
	
	
	public Planning() {

	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getTimeSlotName() {
		return timeSlotName;
	}


	public void setTimeSlotName(String timeSlotName) {
		this.timeSlotName = timeSlotName;
	}


	public String getTimeSlotDescription() {
		return timeSlotDescription;
	}


	public void setTimeSlotDescription(String timeSlotDescription) {
		this.timeSlotDescription = timeSlotDescription;
	}


	public List<VolunteerRegistration> getVolunteerRegistration() {
		return volunteerRegistration;
	}


	public void setVolunteerRegistration(List<VolunteerRegistration> volunteerRegistration) {
		this.volunteerRegistration = volunteerRegistration;
	}
	
}
