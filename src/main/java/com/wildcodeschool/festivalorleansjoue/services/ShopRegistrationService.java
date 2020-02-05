package com.wildcodeschool.festivalorleansjoue.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.GameRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ShopRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRepository;
import com.wildcodeschool.festivalorleansjoue.repository.VolunteerRegistrationRepository;

@Service
public class ShopRegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private VolunteerRegistrationRepository volunteerRegRepository;
	@Autowired
	private ShopRegistrationRepository shopRegistrationRepository;

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

	
	public void updateShopRegistration(ShopRegistration shopRegistration) {
		
		ShopRegistration originalReg = shopRegistrationRepository.getOne(shopRegistration.getId());
		 
		//Tables quantity
		Event event = originalReg.getEvent();
		if (shopRegistration.getTablesQuantity() < 0) {
			shopRegistration.setTablesQuantity(0);
		}
		
		//Registration cost
		shopRegistration.setRegistrationCost(event.getShopPrice());
		
		//Update changes
		originalReg.setAgentProvided(shopRegistration.isAgentProvided());
		originalReg.setTablesQuantity(shopRegistration.getTablesQuantity());
		originalReg.setElectricalSupplyNeed(shopRegistration.isElectricalSupplyNeed());
		originalReg.setComments(shopRegistration.getComments());
		shopRegistrationRepository.save(originalReg);
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

	//TODO
	
	
	public List<ShopRegistration> getAllShopPendingRegs() {
		
		return shopRegistrationRepository.findByState("pending");
	}
	
	
	public List<ShopRegistration> getAllShopCanceledRegs() {
		
		return shopRegistrationRepository.findByState("canceled");
	}
	
	
	public List<ShopRegistration> findAllshopRegistrations() {
		
		List<ShopRegistration> shopRegistrationsList = shopRegistrationRepository.findAll();
		return shopRegistrationsList;
	}


	public List<ShopRegistration> getAllShopValidatedRegs() {
		
		return shopRegistrationRepository.findByState("validated");
	}
	
	
	public List<ShopRegistration> findByEvent(Event event) {
		
		List<ShopRegistration> shopRegistrationsList = shopRegistrationRepository.findByEvent(event);
		return shopRegistrationsList;
	}
	
	
	public List<ShopRegistration> findByEventPending(Event event) {
		
		List<ShopRegistration> shopRegistrationsList = shopRegistrationRepository.findByEventAndState(event, "pending");
		return shopRegistrationsList;
	}
	
	
	public List<ShopRegistration> findBySocietyContain(Event event, String societyName) {
		
		List<ShopRegistration> shopRegistrationsList = shopRegistrationRepository.findByEventAndContainSociety(event.getId(), societyName);
		return shopRegistrationsList;
	}
	
	
	public int getAllShopPendingRegsCount() {
		
		return shopRegistrationRepository.findByState("pending").size();
	}
	
	
	public int getAllShopCanceledRegsCount() {

		return shopRegistrationRepository.findByState("canceled").size();
	}
	
	
	public int getAllShopValidatedRegsCount() {

		return shopRegistrationRepository.findByState("validated").size();
	}

	
	public List<ShopRegistration> findByEventValidated(Event event){
		List<ShopRegistration> shopRegistrationsList = shopRegistrationRepository.findByEventAndState(event, "validated");		
		return shopRegistrationsList;
	}
	
	public List<ShopRegistration> findByEventCanceled(Event event){
		List<ShopRegistration> shopRegistrationsList = shopRegistrationRepository.findByEventAndState(event, "canceled");		
		return shopRegistrationsList;
	}


	public ShopRegistration findShopRegById(Long registrationId) {
		
		return shopRegistrationRepository.getOne(registrationId);
	}
	
}
