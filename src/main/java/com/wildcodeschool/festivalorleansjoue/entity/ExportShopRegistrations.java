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
public class ExportShopRegistrations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long id_inscription;
	private String nom_societé; 
	private String mail_contact; 
	private String nom_evenement;
	private Date debut_evenement; 
	private Date fin_evenement; 
	private Long prix_par_table; 
	private Long nombre_de_tables; 
	private Long nb_tables_demandees; 			 
	private Date date_demande_inscription;
	private int representants;
	private int besoin_electricite;
	private Long cout_inscription;
	private String etat_demande;
	private Long nombre_representants_renseignes;


	public ExportShopRegistrations() {
		
	}


	public Long getId() {
		return id;
	}


	public Long getId_inscription() {
		return id_inscription;
	}


	public String getNom_societé() {
		return nom_societé;
	}


	public String getMail_contact() {
		return mail_contact;
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


	public Long getNb_tables_demandees() {
		return nb_tables_demandees;
	}


	public Date getDate_demande_inscription() {
		return date_demande_inscription;
	}


	public int getRepresentants() {
		return representants;
	}


	public int getBesoin_electricite() {
		return besoin_electricite;
	}


	public Long getCout_inscription() {
		return cout_inscription;
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


	public void setNom_societé(String nom_societé) {
		this.nom_societé = nom_societé;
	}


	public void setMail_contact(String mail_contact) {
		this.mail_contact = mail_contact;
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


	public void setNb_tables_demandees(Long nb_tables_demandees) {
		this.nb_tables_demandees = nb_tables_demandees;
	}


	public void setDate_demande_inscription(Date date_demande_inscription) {
		this.date_demande_inscription = date_demande_inscription;
	}


	public void setRepresentants(int representants) {
		this.representants = representants;
	}


	public void setBesoin_electricite(int besoin_electricite) {
		this.besoin_electricite = besoin_electricite;
	}


	public void setCout_inscription(Long cout_inscription) {
		this.cout_inscription = cout_inscription;
	}


	public void setEtat_demande(String etat_demande) {
		this.etat_demande = etat_demande;
	}


	public Long getNombre_representants_renseignes() {
		return nombre_representants_renseignes;
	}


	public void setNombre_representants_renseignes(Long nombre_representants_renseignes) {
		this.nombre_representants_renseignes = nombre_representants_renseignes;
	}



}
