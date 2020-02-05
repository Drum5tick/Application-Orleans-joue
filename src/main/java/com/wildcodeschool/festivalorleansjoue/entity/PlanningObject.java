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

public class PlanningObject {

	
	private String timeSlotName;
	private String timeSlotDescription;
	private String taskName;
	private Long volunteerCount;
	
	public PlanningObject() {

	}

	public String getTimeSlotName() {
		return timeSlotName;
	}

	public String getTimeSlotDescription() {
		return timeSlotDescription;
	}

	public String getTaskName() {
		return taskName;
	}

	public Long getVolunteerCount() {
		return volunteerCount;
	}

	public void setTimeSlotName(String timeSlotName) {
		this.timeSlotName = timeSlotName;
	}

	public void setTimeSlotDescription(String timeSlotDescription) {
		this.timeSlotDescription = timeSlotDescription;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setVolunteerCount(Long volunteerCount) {
		this.volunteerCount = volunteerCount;
	}
	
}
