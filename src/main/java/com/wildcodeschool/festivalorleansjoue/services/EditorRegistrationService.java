package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.festivalorleansjoue.entity.Agent;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.Game;
import com.wildcodeschool.festivalorleansjoue.entity.Invoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.GameRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ShopRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRepository;
import com.wildcodeschool.festivalorleansjoue.utils.MathUtils;

@Service
public class EditorRegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private ShopRegistrationRepository shopRegistrationRepository;
		
//EDITOR//
	public void submitEditorRegistration(EditorRegistration editorRegistration, Long userId, Long eventId) {
		
		Date subscriptionDate = new Date();
		editorRegistration.setSubscriptionDate(subscriptionDate);		
		Event event = eventRepository.getOne(eventId);
		editorRegistration.setEvent(event);
		if (editorRegistration.getTablesQuantity() > event.getMaxTablesPerEditor()) {
			editorRegistration.setTablesQuantity(event.getMaxTablesPerEditor());
		}
		if (editorRegistration.getTablesQuantity() < 0) {
			editorRegistration.setTablesQuantity(0);
		}
		int tablesQuantity = editorRegistration.getTablesQuantity();
		float tablePrice = event.getPricePerTable();		
		float saleOptionPrice = (editorRegistration.isSaleOption()) ? event.getSaleOptionPrice() : 0.00f;
		int discount = event.getDiscount();
		float registrationCost = MathUtils.registrationCost(tablesQuantity, tablePrice, discount, saleOptionPrice);
		editorRegistration.setRegistrationCost(registrationCost);
		if (editorRegistration.getTablesQuantity() > event.getMaxTablesPerEditor()) {
			editorRegistration.setTablesQuantity(event.getMaxTablesPerEditor());
		}
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = null;
		if (!optionalUser.isEmpty()) {
			user = optionalUser.get();
		}
		editorRegistration.setSociety(user.getSociety());
		editorRegistration.setState("pending");
		
		editorRegistration.updateVolunteerCount();
		saveEditorReg(editorRegistration);
	}

	
	public void updateEditorRegistrationService(EditorRegistration editorRegistration) {
		
		EditorRegistration originalReg = registrationRepository.getOne(editorRegistration.getId());
		 
		//Tables quantity
		Event event = originalReg.getEvent();
		if (editorRegistration.getTablesQuantity() > event.getMaxTablesPerEditor()) {
			editorRegistration.setTablesQuantity(event.getMaxTablesPerEditor());
		} else if (editorRegistration.getTablesQuantity() < 0) {
			editorRegistration.setTablesQuantity(0);
		}
		
		//Registration cost
		int tablesQuantity = editorRegistration.getTablesQuantity();
		float tablePrice = event.getPricePerTable();		
		float saleOptionPrice = (editorRegistration.isSaleOption()) ? event.getSaleOptionPrice() : 0.00f;
		int discount = event.getDiscount();
		float registrationCost = MathUtils.registrationCost(tablesQuantity, tablePrice, discount, saleOptionPrice);		
		editorRegistration.setRegistrationCost(registrationCost);
		
		//Update changes
		originalReg.setTablesQuantity(editorRegistration.getTablesQuantity());
		originalReg.setElectricalSupplyNeed(editorRegistration.isElectricalSupplyNeed());
		originalReg.setSaleOption(editorRegistration.isSaleOption());
		originalReg.setAgentProvided(editorRegistration.isAgentProvided());
		originalReg.setVolunteersNeed(editorRegistration.isVolunteersNeed());
		originalReg.setComments(editorRegistration.getComments());
		originalReg.setRegistrationCost(editorRegistration.getRegistrationCost());	
		originalReg.updateVolunteerCount();
		registrationRepository.save(originalReg);
	}

	
	public void updateEditorRegistration(EditorRegistration editorRegistration) {
		
		registrationRepository.save(editorRegistration);
	}
	
	
	public void saveEditorReg(EditorRegistration editorRegistration) {
		
		registrationRepository.save(editorRegistration);
	}

	public void saveEditorRegWithInvoice(Invoice invoice) {
		
		EditorRegistration toSave = new EditorRegistration();
		toSave = registrationRepository.getOne(invoice.getEditorRegistration().getId());
		toSave.setInvoice(invoice);
		registrationRepository.save(toSave);
	}
	
	public void deleteEditorRegistration(Long registrationId) {
		
		registrationRepository.deleteById(registrationId);	
	}
	
	
	public EditorRegistration findEditorRegById(Long registrationId) {
		
		EditorRegistration reg = registrationRepository.getOne(registrationId);
		return reg;
	}
	
	
	public List<EditorRegistration> getAllEditorPendingRegs() {
		
		return registrationRepository.findByState("pending");
	}
	
	
	public List<EditorRegistration> getAllEditorCanceledRegs() {
		
		return registrationRepository.findByState("canceled");
	}
	
	
	public List<EditorRegistration> findAllEditorRegistrations() {
		
		List<EditorRegistration> editorRegistrationsList = registrationRepository.findAll();
		return editorRegistrationsList;
	}


	public List<EditorRegistration> getAllEditorValidatedRegs() {
		
		return registrationRepository.findByState("validated");
	}
	
	
	public List<EditorRegistration> findByEvent(Event event) {
		
		List<EditorRegistration> editorRegistrationsList = registrationRepository.findByEvent(event);
		return editorRegistrationsList;
	}
	
	
	public List<EditorRegistration> findByEventPending(Event event) {
		
		List<EditorRegistration> editorRegistrationsList = registrationRepository.findByEventAndState(event, "pending");
		return editorRegistrationsList;
	}
	
	
	public List<EditorRegistration> findBySocietyContain(Event event, String societyName) {
		
		List<EditorRegistration> editorRegistrationsList = registrationRepository.findByEventAndContainSociety(event.getId(), societyName);
		return editorRegistrationsList;
	}
	
	
	public int getAllEditorPendingRegsCount() {
		
		return registrationRepository.findByState("pending").size();
	}
	
	
	public int getAllEditorCanceledRegsCount() {

		return registrationRepository.findByState("canceled").size();
	}
	
	
	public int getAllEditorValidatedRegsCount() {

		return registrationRepository.findByState("validated").size();
	}

	
	public List<EditorRegistration> findByEventValidated(Event event){
		List<EditorRegistration> editorRegistrationsList = registrationRepository.findByEventAndState(event, "validated");		
		return editorRegistrationsList;
	}
	
	public List<EditorRegistration> findByEventCanceled(Event event){
		List<EditorRegistration> editorRegistrationsList = registrationRepository.findByEventAndState(event, "canceled");		
		return editorRegistrationsList;
	}
//GAME//
	public void addRegistrationGame(Game game, Long registrationId) {
		
		EditorRegistration editorRegistration = registrationRepository.findById(registrationId).get();
		editorRegistration.getGames().add(game);
		registrationRepository.save(editorRegistration);
	}
	
	
	public void addRegistrationGame(Long gameId, Long registrationId) {
		
		EditorRegistration editorRegistration = registrationRepository.findById(registrationId).get();
		editorRegistration.getGames().add(gameRepository.getOne(gameId));
		registrationRepository.save(editorRegistration);
	}
	
	
	public void deleteRegistrationGame(Game game, Long registrationId) {
		
		EditorRegistration editorRegistration = registrationRepository.findById(registrationId).get();
		List<Game> games = new ArrayList<Game>();
		games = editorRegistration.getGames();
		games.remove(game);
		editorRegistration.setGames(games);
		registrationRepository.save(editorRegistration);
		editorRegistration = registrationRepository.findById(registrationId).get();
	}
	

//SHOP//
	public void submitShopRegistration(ShopRegistration shopRegistration, Long userId, Long eventId) {
		
		Date subscriptionDate = new Date();
		shopRegistration.setSubscriptionDate(subscriptionDate);		
		Event event = eventRepository.getOne(eventId);
		shopRegistration.setEvent(event);		
		shopRegistration.setRegistrationCost(event.getShopPrice());
		
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = null;
		if (!optionalUser.isEmpty()) {
			user = optionalUser.get();
		}
		shopRegistration.setSociety(user.getSociety());
		shopRegistration.setState("pending");
		saveShopReg(shopRegistration);
	}	

	
	public void saveShopReg(ShopRegistration shopRegistration) {
		
		shopRegistrationRepository.save(shopRegistration);
	}
	
	
	public void deleteShopRegistration(Long registrationId) {
		
		shopRegistrationRepository.deleteById(registrationId);	
	}
	
	
	public ShopRegistration findShopById(Long registrationId) {
		
		ShopRegistration reg = shopRegistrationRepository.getOne(registrationId);
		return reg;
	}

	public void saveShopRegWithInvoice(ShopInvoice invoice) {
		
		ShopRegistration toSave = new ShopRegistration();
		toSave = shopRegistrationRepository.getOne(invoice.getShopRegistration().getId());
		toSave.setShopInvoice(invoice);
		shopRegistrationRepository.save(toSave);
	}

//AGENT//
	public void addRegistrationAgent(Agent agent, Long registrationId, Long tablesQuantity) {
		
		EditorRegistration editorRegistration = registrationRepository.findById(registrationId).get();		
		editorRegistration.getAgents().add(agent);
		
		editorRegistration.updateVolunteerCount();	
		registrationRepository.save(editorRegistration);
	}
	
	
	public void addShopRegistrationAgent(Agent agent, Long registrationId) {
		
		ShopRegistration shopRegistration = shopRegistrationRepository.findById(registrationId).get();
		shopRegistration.getAgents().add(agent);
		shopRegistrationRepository.save(shopRegistration);
	}
	
	
	public void deleteRegistrationAgent(Agent agent, Long registrationId, Double tablesQuantity) {
		
		EditorRegistration editorRegistration = registrationRepository.findById(registrationId).get();
		List<Agent> agents = new ArrayList<Agent>();
		agents = editorRegistration.getAgents();
		agents.remove(agent);
		editorRegistration.setAgents(agents);
		
		editorRegistration.updateVolunteerCount();		
		registrationRepository.save(editorRegistration);
	}
	
	
	public void deleteShopRegistrationAgent(Agent agent, Long registrationId) {
		
		ShopRegistration shopRegistration = shopRegistrationRepository.findById(registrationId).get();
		List<Agent> agents = new ArrayList<Agent>();
		agents = shopRegistration.getAgents();
		agents.remove(agent);
		shopRegistration.setAgents(agents);
		shopRegistrationRepository.save(shopRegistration);
		shopRegistration = shopRegistrationRepository.findById(registrationId).get();
	}


}
