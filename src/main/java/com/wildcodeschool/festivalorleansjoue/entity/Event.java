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
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private short tables;
	@Lob
	@Column
	private String description;
	private byte protozoneTables;
	private byte maxTablesPerEditor;
	private float pricePerTable;
	private float saleOptionPrice;
	private float shopPrice;
	private int discount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventBeginningDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventEndingDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date editorsRegistrationBeginDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date editorsRegistrationEndDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shopsRegistrationBeginDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shopsRegistrationEndDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date volunteersRegistrationBeginDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date volunteersRegistrationEndDate;
	private boolean registrationOpen = false;
	private boolean registrationBefore = false;
	private String registrationMessage;
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<EventTask> eventTasks = new ArrayList<EventTask>();
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<EditorRegistration> editorRegList = new ArrayList<>();
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<VolunteerRegistration> volunteerRegList = new ArrayList<>();
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<ShopRegistration> shopRegList = new ArrayList<>();
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<ProtozoneRegistration> protozoneRegList = new ArrayList<>();

	public Event() {
		
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


	public short getTables() {

		return this.tables;
	}
	

	public void setTables(short tables) {

		this.tables = tables;
	}
	

	public byte getProtozoneTables() {

		return this.protozoneTables;
	}
	

	public void setProtozoneTables(byte protozoneTables) {

		this.protozoneTables = protozoneTables;
	}
	

	public Date getEventBeginningDate() {

		return this.eventBeginningDate;
	}
	

	public void setEventBeginningDate(Date eventBeginningDate) {

		this.eventBeginningDate = eventBeginningDate;
	}
	

	public Date getEventEndingDate() {

		return this.eventEndingDate;
	}
	

	public void setEventEndingDate(Date eventEndingDate) {

		this.eventEndingDate = eventEndingDate;
	}
	

	public Date getEditorsRegistrationBeginDate() {

		return this.editorsRegistrationBeginDate;
	}
	

	public void setEditorsRegistrationBeginDate(Date editorsRegistrationBeginDate) {

		this.editorsRegistrationBeginDate = editorsRegistrationBeginDate;
	}
	

	public Date getEditorsRegistrationEndDate() {

		return this.editorsRegistrationEndDate;
	}
	

	public void setEditorsRegistrationEndDate(Date editorsRegistrationEndDate) {

		this.editorsRegistrationEndDate = editorsRegistrationEndDate;
	}
	

	public Date getShopsRegistrationBeginDate() {

		return this.shopsRegistrationBeginDate;
	}
	

	public void setShopsRegistrationBeginDate(Date shopsRegistrationBeginDate) {

		this.shopsRegistrationBeginDate = shopsRegistrationBeginDate;
	}
	

	public Date getShopsRegistrationEndDate() {

		return this.shopsRegistrationEndDate;
	}
	

	public void setShopsRegistrationEndDate(Date shopsRegistrationEndDate) {

		this.shopsRegistrationEndDate = shopsRegistrationEndDate;
	}

	public Date getVolunteersRegistrationBeginDate() {

		return this.volunteersRegistrationBeginDate;
	}
	

	public void setVolunteersRegistrationBeginDate(Date volunteersRegistrationBeginDate) {

		this.volunteersRegistrationBeginDate = volunteersRegistrationBeginDate;
	}
	

	public Date getVolunteersRegistrationEndDate() {

		return this.volunteersRegistrationEndDate;
	}
	

	public void setVolunteersRegistrationEndDate(Date volunteersRegistrationEndDate) {

		this.volunteersRegistrationEndDate = volunteersRegistrationEndDate;
	}
	

	public byte getMaxTablesPerEditor() {

		return this.maxTablesPerEditor;
	}
	

	public void setMaxTablesPerEditor(byte maxTablesPerEditor) {

		this.maxTablesPerEditor = maxTablesPerEditor;
	}
	

	public float getPricePerTable() {
		
		return this.pricePerTable;
	}


	public void setPricePerTable(float pricePerTable) {
		
		this.pricePerTable = pricePerTable;
	}


	public float getSaleOptionPrice() {
		
		return this.saleOptionPrice;
	}


	public void setSaleOptionPrice(float saleOptionPrice) {
		
		this.saleOptionPrice = saleOptionPrice;
	}


	public float getShopPrice() {
		return shopPrice;
	}


	public void setShopPrice(float shopPrice) {
		this.shopPrice = shopPrice;
	}


	public int getDiscount() {
		
		return this.discount;
	}


	public void setDiscount(int discount) {
		
		this.discount = discount;
	}


	
	

	public String getDescription() {

		return this.description;
	}
	

	public void setDescription(String description) {

		this.description = description;
	}
	

	public boolean isRegistrationOpen() {

		return this.registrationOpen;
	}
	

	public void setRegistrationOpen(boolean registrationOpen) {
		
		this.registrationOpen = registrationOpen;
	}
	

	
	public boolean isRegistrationBefore() {

		return this.registrationBefore;
	}
	

	public void setRegistrationBefore(boolean registrationBefore) {

		this.registrationBefore = registrationBefore;
	}
	

	public String getRegistrationMessage() {

		return this.registrationMessage;
	}
	

	public void setRegistrationMessage(String registrationMessage) {

		this.registrationMessage = registrationMessage;
	}
	

	public List<EditorRegistration> getEditorRegList() {
		
		return this.editorRegList;
	}


	public void setEditorRegList(List<EditorRegistration> editorRegList) {
		
		this.editorRegList = editorRegList;
	}


	public List<VolunteerRegistration> getVolunteerRegList() {
		
		return volunteerRegList;
	}


	public void setVolunteerRegList(List<VolunteerRegistration> volunteerRegList) {
		
		this.volunteerRegList = volunteerRegList;
	}
	
	
	public List<ShopRegistration> getShopRegList() {
		return shopRegList;
	}


	public void setShopRegList(List<ShopRegistration> shopRegList) {
		this.shopRegList = shopRegList;
	}
	
	public List<EventTask> getEventTasks() {
		return eventTasks;
	}
	
	
	public void setEventTasks(List<EventTask> eventTasks) {
		this.eventTasks = eventTasks;
	}


	public List<ProtozoneRegistration> getProtozoneRegList() {
		return protozoneRegList;
	}


	public void setProtozoneRegList(List<ProtozoneRegistration> protozoneRegList) {
		this.protozoneRegList = protozoneRegList;
	}
	
	
}
