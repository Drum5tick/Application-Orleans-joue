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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	String name;
	String author;
	String editor;
	String distributor;
	String category;
	int price;
	@Lob
	String description;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	Date publicationDate;
	String picture;
	String webLink;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "games")
	//@ManyToMany
	//@JoinTable(name = "game_registration", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "registration_id"))
	private List<EditorRegistration> editorRegistrations = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "society_id")
	private Society society;
	
		
	public Game() {
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


	public String getAuthor() {
		
		return this.author;
	}


	public void setAuthor(String author) {
		
		this.author = author;
	}


	public String getDescription() {
		
		return this.description;
	}


	public void setDescription(String descrption) {
		
		this.description = descrption;
	}


	public Date getPublicationDate() {
		
		return this.publicationDate;
	}


	public void setPublicationDate(Date publicationDate) {
		
		this.publicationDate = publicationDate;
	}


	public String getPicture() {
		
		return this.picture;
	}


	public void setPicture(String picture) {
		
		this.picture = picture;
	}


	public String getWebLink() {
		
		return this.webLink;
	}


	public void setWebLink(String webLink) {
		
		this.webLink = webLink;
	}


	public Society getSociety() {
		
		return this.society;
	}


	public void setSociety(Society society) {
		
		this.society = society;
	}


	public List<EditorRegistration> getRegistrations() {
		
		return this.editorRegistrations;
	}


	public void setRegistrations(List<EditorRegistration> editorRegistrations) {
		
		this.editorRegistrations = editorRegistrations;
	}


	public String getEditor() {
		return editor;
	}


	public void setEditor(String editor) {
		this.editor = editor;
	}


	public String getDistributor() {
		return distributor;
	}


	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}	
	
}
