package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SocietyReferent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "societyReferent")
	private List<Referent> referents = new ArrayList<Referent>();
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "society_id", referencedColumnName = "id")
	private Society society;
	private boolean masterReferent;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public SocietyReferent() {

	}
	
	
	public Long getId() {
		
		return this.id;
	}
	

	public void setId(Long id) {
		
		this.id = id;
	}
	

	public List<Referent> getReferents() {
		
		return this.referents;
	}
	

	public void setReferents(List<Referent> referents) {
		
		this.referents = referents;
	}
	

	public Society getSociety() {
		
		return this.society;
	}
	

	public void setSociety(Society society) {
		
		this.society = society;
	}
	

	public boolean isMasterReferent() {
		
		return this.masterReferent;
	}
	

	public void setMasterReferent(boolean masterReferent) {
		
		this.masterReferent = masterReferent;
	}
	

	public User getUser() {
		
		return this.user;
	}
	

	public void setUser(User user) {
		
		this.user = user;
	}

}
