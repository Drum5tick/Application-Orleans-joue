package com.wildcodeschool.festivalorleansjoue.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRepository;
import com.wildcodeschool.festivalorleansjoue.repository.VolunteerRegistrationRepository;

@Service
public class VolunteerRegistrationService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	VolunteerRegistrationRepository volunteerRegRepository;
	
	
	public void submitVolunteerRegistration(VolunteerRegistration volunteerRegistration, Long eventId, Long userId) {
		
		volunteerRegistration.setSubscriptionDate(new Date());
		volunteerRegistration.setState("pending");
		Event event = eventRepository.findById(eventId).get();
		User user = userRepository.findById(userId).get();
		volunteerRegistration.setEvent(event);
		volunteerRegistration.setUser(user);
		
		volunteerRegRepository.save(volunteerRegistration);
	}
	
	
	public void updateVolunteerRegistrationService(VolunteerRegistration volunteerRegistration) {
		
		VolunteerRegistration originalReg = volunteerRegRepository.getOne(volunteerRegistration.getId());
		
		originalReg.setChosenTasks(volunteerRegistration.getChosenTasks());
		originalReg.setChosenTimeSlot(volunteerRegistration.getChosenTimeSlot());
		originalReg.setComments(volunteerRegistration.getComments());
		volunteerRegRepository.save(originalReg);
	}
	
	public void updateVolunteerRegistration(VolunteerRegistration volunteerRegistration) {
		
		volunteerRegRepository.save(volunteerRegistration);
	}	

	
	public void saveVolunteerReg(VolunteerRegistration volunteerRegistration) {
		
		volunteerRegRepository.save(volunteerRegistration);
	}
	
	
	public void deleteVolunteerRegistration(Long registrationId) {
		
		volunteerRegRepository.deleteById(registrationId);	
	}
	
	
	public VolunteerRegistration findVolunteerById(Long registrationId) {
		
		VolunteerRegistration reg = volunteerRegRepository.getOne(registrationId);
		return reg;
	}


	public List<VolunteerRegistration> findVolunteerRegByUser(User connectedUser) {
		
		List<VolunteerRegistration> volunteerRegsList = volunteerRegRepository.findByUser(connectedUser);
		return volunteerRegsList;
	}
	
	
	public List<VolunteerRegistration> findAllVoluteerByEvent(Event event) {
		
		List<VolunteerRegistration> listVolunteerByEvent = volunteerRegRepository.findAllVolunteerRegistrationByEvent(event);
		return listVolunteerByEvent;
	}


}
