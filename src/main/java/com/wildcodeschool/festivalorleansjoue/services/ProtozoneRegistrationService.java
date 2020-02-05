package com.wildcodeschool.festivalorleansjoue.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ProtozoneRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRepository;

@Service
public class ProtozoneRegistrationService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProtozoneRegistrationRepository protozoneRegistrationRepository;
	
	
	public void submitProtozoneRegistration(ProtozoneRegistration protozoneRegistration, Long userId, Long eventId) {
		
		protozoneRegistration.setSubscriptionDate(new Date());
		protozoneRegistration.setState("pending");
		Event event = eventRepository.findById(eventId).get();
		User user = userRepository.findById(userId).get();
		protozoneRegistration.setEvent(event);
		protozoneRegistration.setUser(user);
		
		protozoneRegistrationRepository.save(protozoneRegistration);
	}
	
	
	public void updateProtozoneRegistrationService(ProtozoneRegistration protozoneRegistration) {
		
		ProtozoneRegistration originalReg = protozoneRegistrationRepository.getOne(protozoneRegistration.getId());
		
		originalReg.setComments(protozoneRegistration.getComments());
		protozoneRegistrationRepository.save(originalReg);
	}
	
	public void updateProtozoneRegistration(ProtozoneRegistration protozoneRegistration) {
		
		protozoneRegistrationRepository.save(protozoneRegistration);
	}	

	
	public void saveProtozoneRegistration(ProtozoneRegistration protozoneRegistration) {
		
		protozoneRegistrationRepository.save(protozoneRegistration);
	}
	
	
	public void deleteProtozoneRegistration(Long registrationId) {
		
		protozoneRegistrationRepository.deleteById(registrationId);	
	}
	
	
	public ProtozoneRegistration findProtozoneById(Long registrationId) {
		
		ProtozoneRegistration reg = protozoneRegistrationRepository.getOne(registrationId);
		return reg;
	}


	public List<ProtozoneRegistration> findProtozoneRegistrationByUser(User connectedUser) {
		
		List<ProtozoneRegistration> protozoneRegsList = protozoneRegistrationRepository.findByUser(connectedUser);
		return protozoneRegsList;
	}
	
	
	public List<ProtozoneRegistration> findAllProtozoneByEvent(Event event) {
		
		List<ProtozoneRegistration> listProtozoneByEvent = protozoneRegistrationRepository.findAllProtozoneRegistrationByEvent(event);
		return listProtozoneByEvent;
	}


	public Object findByEventPending(Event event) {
		List<ProtozoneRegistration> protozoneRegistrationsList = protozoneRegistrationRepository.findByEventAndState(event, "pending");
		return protozoneRegistrationsList;
	}


	public Object findByEvent(Long registrationId) {
		
		ProtozoneRegistration reg = protozoneRegistrationRepository.getOne(registrationId);
		return reg;
	}


	public Object findByEventValidated(Event event) {
		List<ProtozoneRegistration> protozoneRegistrationsList = protozoneRegistrationRepository.findByEventAndState(event, "validated");		
		return protozoneRegistrationsList;
	}


	public Object findByEventCanceled(Event event) {
		List<ProtozoneRegistration> protozoneRegistrationsList = protozoneRegistrationRepository.findByEventAndState(event, "canceled");		
		return protozoneRegistrationsList;
	}


	public Object findByEvent(Event event) {
		List<ProtozoneRegistration> protozoneRegistrationsList = protozoneRegistrationRepository.findByEvent(event);
		return protozoneRegistrationsList;
	}


}
