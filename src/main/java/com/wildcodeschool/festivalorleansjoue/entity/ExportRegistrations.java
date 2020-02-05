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
public class ExportRegistrations {

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
	private Long nb_jeux_inscrits; 
	private int option_vente; 
	private Date date_demande_inscription; 
	private int besoin_volontaire;
	private int representants;
	private int besoin_electricite;
	private Long cout_inscription;
	private String etat_demande;
	private Long numero_facture;


	public ExportRegistrations() {
		
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


	public Long getNb_jeux_inscrits() {
		return nb_jeux_inscrits;
	}


	public int getOption_vente() {
		return option_vente;
	}


	public Date getDate_demande_inscription() {
		return date_demande_inscription;
	}


	public int getBesoin_volontaire() {
		return besoin_volontaire;
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


	public Long getNumero_facture() {
		return numero_facture;
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


	public void setNb_jeux_inscrits(Long nb_jeux_inscrits) {
		this.nb_jeux_inscrits = nb_jeux_inscrits;
	}


	public void setOption_vente(int option_vente) {
		this.option_vente = option_vente;
	}


	public void setDate_demande_inscription(Date date_demande_inscription) {
		this.date_demande_inscription = date_demande_inscription;
	}


	public void setBesoin_volontaire(int besoin_volontaire) {
		this.besoin_volontaire = besoin_volontaire;
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


	public void setNumero_facture(Long numero_facture) {
		this.numero_facture = numero_facture;
	}
	

}
