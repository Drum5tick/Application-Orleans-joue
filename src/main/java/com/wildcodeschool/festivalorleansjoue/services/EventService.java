package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ProtozoneRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ShopRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.VolunteerRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.utils.DateUtils;

@Service
public class EventService implements IEventService{

	@Autowired
	EventRepository eventRepository;
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	VolunteerRegistrationRepository volunteerRegistrationRepository;
	@Autowired
	ShopRegistrationRepository shopRegistrationRepository;
	@Autowired
	ProtozoneRegistrationRepository protozoneRegistrationRepository;
	
	@Override
	public List<Event> returnEvent(Date today){

		List<Event> events = new ArrayList<>();
		events = eventRepository.findByEventEndingDateAfter(today);
		return events;
	}

	@Override
	public void setMessage(User user, List<Event> events, Date today) {
		if (user.getUserRole().getWording().equals("EDITOR")) {
			for (Event event2 : events) {
				if (event2.getEditorsRegistrationBeginDate().before(today) && event2.getEditorsRegistrationEndDate().after(today)) {
					event2.setRegistrationOpen(true);
				} else
					DateUtils.registrationCondition(today, event2);
			}
		}

		else if (user.getUserRole().getWording().equals("SHOP")) {
			for (Event event2 : events) {
				if (event2.getShopsRegistrationBeginDate().before(today) && event2.getShopsRegistrationEndDate().after(today)) {
					event2.setRegistrationOpen(true);
				} else
					DateUtils.registrationCondition(today, event2);
			}
		}

		else if (user.getUserRole().getWording().equals("VOLUNTEER")) {
			for (Event event2 : events) {
				if (event2.getVolunteersRegistrationBeginDate().before(today) && event2.getVolunteersRegistrationEndDate().after(today)) {
					event2.setRegistrationOpen(true);
				} else
					DateUtils.registrationCondition(today, event2);
			}
		}
		
		else if (user.getUserRole().getWording().equals("PROTOZONE")) {
			for (Event event2 : events) {
				if (event2.getEditorsRegistrationBeginDate().before(today) && event2.getEditorsRegistrationEndDate().after(today)) {
					event2.setRegistrationOpen(true);
				} else
					DateUtils.registrationCondition(today, event2);
			}
		}
	}
	

	public Event findById(Long id) {
				
		return eventRepository.getOne(id);
	}
	

	public Event findLastEvent() {
		
		return eventRepository.findTopByOrderByEventBeginningDateDesc();
  }

  
	public List<Event> findAll() {
    
		return eventRepository.findAll();
	}

	public List<Integer> updateReservedTables() {
		
		List<Event> events = returnEvent(new Date());
		List<EditorRegistration> editorRegs;
		List<Integer> reservedTablesList = new ArrayList<>();
		int tableSum = 0;
		for (Event event : events) {
			editorRegs = event.getEditorRegList();
			for (EditorRegistration editorReg : editorRegs ) {
				if (editorReg.getState().equals("validated"))
					tableSum += editorReg.getTablesQuantity();
			}
			reservedTablesList.add(tableSum);
		}
		return reservedTablesList;
	}
	
	public List<Integer> updatePendingTables() {
		
		List<Event> events = returnEvent(new Date());
		List<EditorRegistration> editorRegs;
		List<Integer> pendingTablesList = new ArrayList<>();
		int tableSum = 0;
		for (Event event : events) {
			editorRegs = event.getEditorRegList();
			for (EditorRegistration editorReg : editorRegs ) {
				if (editorReg.getState().equals("pending"))
					tableSum += editorReg.getTablesQuantity();
			}
			pendingTablesList.add(tableSum);
		}
		return pendingTablesList;
	}
	

	public List<Integer> getEditorPendingRegsCount() {
		
		List<Event> events = eventRepository.findAll();
		int editorPendingRegsCount = 0;		
		List<Integer> editorPendingRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			editorPendingRegsCount = registrationRepository.findByEventAndState(event, "pending").size();
			editorPendingRegsCountList.add(editorPendingRegsCount);
		}
		return editorPendingRegsCountList;
	}
	

	public List<Integer> getEditorValidatedRegsCount() {
		
		List<Event> events = eventRepository.findAll();
		int editorValidatedRegsCount = 0;		
		List<Integer> editorValidatedRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			editorValidatedRegsCount = registrationRepository.findByEventAndState(event, "validated").size();
			editorValidatedRegsCountList.add(editorValidatedRegsCount);
		}
		return editorValidatedRegsCountList;
	}
	
	
	public List<Integer> getShopPendingRegsCount() {
		
		List<Event> events = eventRepository.findAll();
		int shopPendingRegsCount = 0;
		List<Integer> shopPendingRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			shopPendingRegsCount = shopRegistrationRepository.findByEventAndState(event, "pending").size();
			shopPendingRegsCountList.add(shopPendingRegsCount);
		}
		return shopPendingRegsCountList;		
	}
	
	
	public List<Integer> getShopValidatedRegsCount() {
	
		List<Event> events = eventRepository.findAll();
		int shopValidatedRegsCount = 0;
		List<Integer> shopValidatedRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			shopValidatedRegsCount = shopRegistrationRepository.findByEventAndState(event, "validated").size();
			shopValidatedRegsCountList.add(shopValidatedRegsCount);
		}
		return shopValidatedRegsCountList;
	}
	
	public List<Integer> getProtozonePendingRegsCount() {
		
		List<Event> events = eventRepository.findAll();
		int shopPendingRegsCount = 0;
		List<Integer> shopPendingRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			shopPendingRegsCount = protozoneRegistrationRepository.findByEventAndState(event, "pending").size();
			shopPendingRegsCountList.add(shopPendingRegsCount);
		}
		return shopPendingRegsCountList;		
	}
	
	
	public List<Integer> getProtozoneValidatedRegsCount() {
	
		List<Event> events = eventRepository.findAll();
		int protozoneValidatedRegsCount = 0;
		List<Integer> protozoneValidatedRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			protozoneValidatedRegsCount = protozoneRegistrationRepository.findByEventAndState(event, "validated").size();
			protozoneValidatedRegsCountList.add(protozoneValidatedRegsCount);
		}
		return protozoneValidatedRegsCountList;
	}

	public List<Integer> getVolunteerRegsCount() {
		
		List<Event> events = eventRepository.findAll();
		int volunteerRegsCount = 0;
		List<Integer> volunteerValidatedRegsCountList = new ArrayList<Integer>();
		for (Event event : events) {
			volunteerRegsCount = event.getVolunteerRegList().size();
			volunteerValidatedRegsCountList.add(volunteerRegsCount);
		}
		return volunteerValidatedRegsCountList;
	}

}
