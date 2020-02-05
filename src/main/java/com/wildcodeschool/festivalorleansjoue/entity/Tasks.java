package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tasks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List<EventTask> eventTasks = new ArrayList<EventTask>();
	

	public Tasks() {

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


	public List<EventTask> getEventTasks() {
		return eventTasks;
	}


	public void setEventTasks(List<EventTask> eventTasks) {
		this.eventTasks = eventTasks;
	}
	
}
