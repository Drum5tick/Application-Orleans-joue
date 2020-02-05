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
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class ExportVolunteerPlanning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long id_inscription;
	private String prenom;
	private String nom;
	private String email;
	private String nom_evenement;
    private String tache;
    private String jour;
    private String tranche_horaire;


	public ExportVolunteerPlanning() {
		
	}


	public Long getId() {
		return id;
	}


	public Long getId_inscription() {
		return id_inscription;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getNom() {
		return nom;
	}


	public String getEmail() {
		return email;
	}


	public String getNom_evenement() {
		return nom_evenement;
	}


	public String getTache() {
		return tache;
	}


	public String getJour() {
		return jour;
	}


	public String getTranche_horaire() {
		return tranche_horaire;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setId_inscription(Long id_inscription) {
		this.id_inscription = id_inscription;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setNom_evenement(String nom_evenement) {
		this.nom_evenement = nom_evenement;
	}


	public void setTache(String tache) {
		this.tache = tache;
	}


	public void setJour(String jour) {
		this.jour = jour;
	}


	public void setTranche_horaire(String tranche_horaire) {
		this.tranche_horaire = tranche_horaire;
	}




}
