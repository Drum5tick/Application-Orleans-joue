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
public class ExportVolunteerRegistrations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long id_inscription;
	private String prenom;
	private String nom;
	private String email;
	private String nom_evenement;
	private Date debut_evenement;
	private Date fin_evenement;
	private Long prix_par_table;
	private Long nombre_de_tables;
	private Long besoin_volontaires_total_evenement;
	private Date date_demande_inscription;
	private String etat_demande;


	public ExportVolunteerRegistrations() {
		
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


	public Date getDebut_evenement() {
		return debut_evenement;
	}


	public Date getFin_evenement() {
		return fin_evenement;
	}


	public Long getPrix_par_table() {
		return prix_par_table;
	}


	public Long getNombre_de_tables() {
		return nombre_de_tables;
	}


	public Long getBesoin_volontaires_total_evenement() {
		return besoin_volontaires_total_evenement;
	}


	public Date getDate_demande_inscription() {
		return date_demande_inscription;
	}


	public String getEtat_demande() {
		return etat_demande;
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


	public void setDebut_evenement(Date debut_evenement) {
		this.debut_evenement = debut_evenement;
	}


	public void setFin_evenement(Date fin_evenement) {
		this.fin_evenement = fin_evenement;
	}


	public void setPrix_par_table(Long prix_par_table) {
		this.prix_par_table = prix_par_table;
	}


	public void setNombre_de_tables(Long nombre_de_tables) {
		this.nombre_de_tables = nombre_de_tables;
	}


	public void setBesoin_volontaires_total_evenement(Long besoin_volontaires_total_evenement) {
		this.besoin_volontaires_total_evenement = besoin_volontaires_total_evenement;
	}


	public void setDate_demande_inscription(Date date_demande_inscription) {
		this.date_demande_inscription = date_demande_inscription;
	}


	public void setEtat_demande(String etat_demande) {
		this.etat_demande = etat_demande;
	}

}
