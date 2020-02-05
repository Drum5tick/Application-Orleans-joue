package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.wildcodeschool.festivalorleansjoue.entity.Address;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.EventTask;
import com.wildcodeschool.festivalorleansjoue.entity.Planning;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.entity.Tasks;
import com.wildcodeschool.festivalorleansjoue.entity.TimeSlot;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.EventTaskRepository;
import com.wildcodeschool.festivalorleansjoue.repository.PlanningRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ProtozoneRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.ShopRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.SocietyRepository;
import com.wildcodeschool.festivalorleansjoue.repository.TasksRepository;
import com.wildcodeschool.festivalorleansjoue.repository.TimeSlotRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRoleRepository;
import com.wildcodeschool.festivalorleansjoue.repository.VolunteerRegistrationRepository;

@Service
public class AdminService {


	@Autowired
	private EventService eventService;
	@Autowired
	private EditorRegistrationService editorRegistrationService;
	@Autowired
	private VolunteerRegistrationService volunteerRegistrationService;
	@Autowired
	private ProtozoneRegistrationService protozoneRegistrationService;
	@Autowired
	private UserService userService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private TasksRepository taskRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private EventTaskRepository eventTaskRepository;
	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private VolunteerRegistrationRepository volunteerRegistrationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShopRegistrationService shopRegistrationService;
	@Autowired
	private ShopRegistrationRepository shopRegistrationRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private PlanningRepository planningRepository;
	@Autowired
	private SocietyRepository societyRepository;
	@Autowired
	ProtozoneRegistrationRepository protozoneRegistrationRepository;
	@Autowired
	private PlanningService planningService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private ModelAndView mav = new ModelAndView();
	private String toggleCard = "close";

	public ModelAndView getMav() {
		return this.mav;
	}
	
	
	public void setMav(ModelAndView mav) {		
		this.mav = mav;
	}
	
//View Section
	
	public ModelAndView buildAdminHomeView() {
		this.mav.addObject("activeEvents", eventService.returnEvent(new Date()));
		this.mav.addObject("editorPendingRegs", eventService.getEditorPendingRegsCount());
		this.mav.addObject("editorValidatedRegs", eventService.getEditorValidatedRegsCount());
		this.mav.addObject("shopPendingRegs", eventService.getShopPendingRegsCount());
		this.mav.addObject("shopValidatedRegs", eventService.getShopValidatedRegsCount());
		this.mav.addObject("protozonePendingRegs", eventService.getProtozonePendingRegsCount());
		this.mav.addObject("protozoneValidatedRegs", eventService.getProtozoneValidatedRegsCount());
		this.mav.addObject("volunteerRegs", eventService.getVolunteerRegsCount());
		this.mav.addObject("connectedUser", userService.returnConnectedUser());
		this.mav.addObject("reservedTables", eventService.updateReservedTables());
		this.mav.addObject("pendindTables", eventService.updatePendingTables());
		this.mav.setViewName("adminHome");
		this.mav.addObject("page","adminHome");
		return this.mav;
	}


	public ModelAndView buildAdminEventView() {
    
		Date today = new Date();
		this.mav.setViewName("adminEvent");
		this.mav.addObject("historicalEvents", eventService.findAll());
		this.mav.addObject("events", eventService.returnEvent(today));
		this.mav.addObject("tasks", taskRepository.findAll());
		this.mav.addObject("timeSlots", timeSlotRepository.findAllByOrderByOrdernumberAsc());
		this.mav.addObject("page", "adminEvent");
		this.mav.addObject("toggleCard", toggleCard);

		return this.mav;
	}
	

	public ModelAndView buildAdminAccountView() {

		this.mav.addObject("adminAccounts", userService.findByRole("ADMIN"));
		this.mav.addObject("editorAccounts", userService.findByRole("EDITOR"));
		this.mav.addObject("shopAccounts", userService.findByRole("SHOP"));
		this.mav.addObject("volunteerAccounts", userService.findByRole("VOLUNTEER"));
		this.mav.addObject("protozoneAccounts", userService.findByRole("PROTOZONE"));
		this.mav.addObject("userRoles", userRoleRepository.findAll());
		this.mav.addObject("page","adminAccounts");
		this.mav.setViewName("adminAccounts");
		this.mav.addObject("toggleCard", toggleCard);
		return this.mav;
	}
	

	public ModelAndView buildAdminRegistrationsView(Long eventId) {
		Date today = new Date();
		Event event = new Event();
		this.mav.clear();
		this.mav.setViewName("adminRegistrations");
		this.mav.addObject("events",eventService.returnEvent(today));
		if(eventId == null) {
			event = eventService.findLastEvent();
		}
		else {
			event = eventService.findById(eventId);		
		}
		if (event != null) {
			this.mav.addObject("lastEvent",event);
			this.mav.addObject("editorRegistrationPending",editorRegistrationService.findByEventPending(eventService.findById(event.getId())));
			this.mav.addObject("editorRegistrationList",editorRegistrationService.findByEvent(eventService.findById(event.getId())));
			this.mav.addObject("editorRegistrationValidated",editorRegistrationService.findByEventValidated(eventService.findById(event.getId())));
			this.mav.addObject("editorRegistrationCanceled",editorRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
			String registrationPageToggleCard = "colseAll ValidatedRegistrations";
			if (!registrationPageToggleCard.contains(this.toggleCard));
				this.toggleCard = "PendingRegistrations";			
		}
		this.mav.addObject("page","gestionDesInscriptions");
		this.mav.addObject("toggleCard", this.toggleCard);
		return this.mav;

	}
	
	
	public ModelAndView buildAdminVolunteerView(Long eventId, String toggleCard) {

		Date today = new Date();
		Event event = new Event();
		this.mav.clear();
		this.mav.setViewName("adminVolunteers");
		this.mav.addObject("events",eventService.returnEvent(today));
		if(eventId == null) {
			event = eventService.findLastEvent();
		}
		else {
			event = eventService.findById(eventId);		
		}
		if(event != null) {
			
			this.mav.addObject("planningResult", planningService.planningCalcul(event));
			this.mav.addObject("tasks", taskRepository.findAll());
			this.mav.addObject("timeSlots", timeSlotRepository.findAll());
			this.mav.addObject("lastEvent",event);
			List<EventTask> eventTasks = eventTaskRepository.findByEventId(event.getId());
			List<Tasks> tasks = new ArrayList<Tasks>();
			for (EventTask eventTask : eventTasks){
				tasks.add(eventTask.getTask());
			}
			this.mav.addObject("eventTasks",tasks);
			List<VolunteerRegistration> volunteerRegistrations = volunteerRegistrationService.findAllVoluteerByEvent(eventService.findById(event.getId()));
			this.mav.addObject("volunteerRegistrationList", volunteerRegistrations);
			this.mav.addObject("volunteerSchedule", volunteerRegistrations);
		}
		this.mav.addObject("page","adminVolunteers");
		this.mav.addObject("toggleCard", toggleCard);
		return this.mav;
	}
	
	
	public ModelAndView buildAdminProtozoneView(Long eventId, String toggleCard) {

		Date today = new Date();
		Event event = new Event();
		this.mav.clear();
		this.mav.setViewName("adminProtozone");
		this.mav.addObject("events",eventService.returnEvent(today));
		if(eventId == null) {
			event = eventService.findLastEvent();
		}
		else {
			event = eventService.findById(eventId);		
		}
		if (event !=null) {
			
			this.mav.addObject("protozoneRegistrationPending",protozoneRegistrationService.findByEventPending(eventService.findById(event.getId())));
			this.mav.addObject("protozoneRegistrationList",protozoneRegistrationService.findByEvent(eventService.findById(event.getId())));
			this.mav.addObject("protozoneRegistrationValidated",protozoneRegistrationService.findByEventValidated(eventService.findById(event.getId())));
			this.mav.addObject("protozoneRegistrationCanceled",protozoneRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
			this.mav.addObject("lastEvent",event);
		}
		this.mav.addObject("page","adminProtozone");
		this.mav.addObject("toggleCard", toggleCard);
		return this.mav;
	}
	
//Editor Registration Section
	
	public ModelAndView buildAdminRegistrationsSearch(Long eventId, String societyName) {
		Date today = new Date();
		Event event = new Event();
		this.mav.setViewName("adminRegistrations");
		this.mav.addObject("events",eventService.returnEvent(today));
		if(eventId == null) {
			event = eventService.findLastEvent();
		}
		else {
			event = eventService.findById(eventId);		
		}
		if( event != null) {
			
			this.mav.addObject("lastEvent",event);
			this.mav.addObject("editorRegistrationPending",editorRegistrationService.findByEventPending(eventService.findById(event.getId())));
			this.mav.addObject("editorRegistrationList",editorRegistrationService.findByEvent(eventService.findById(event.getId())));
			this.mav.addObject("editorRegistrationValidated",editorRegistrationService.findByEventValidated(eventService.findById(event.getId())));
			this.mav.addObject("editorRegistrationCanceled",editorRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
			this.mav.addObject("societySearched", societyName);
		}
		
		this.mav.addObject("page","gestionDesInscriptions");
		this.mav.setViewName("adminRegistrations");
		this.mav.addObject("toggleCard", "SearchRegistrations");
		return this.mav;
	}
	
	public void setToggleforSelectedRegistration() {
		toggleCard="PendingRegistrations";
		
	}
	
	  
	public void setRegistrationsResults(Long eventId, String societyName, String orderBy) {	
		this.mav.clear();
		List<EditorRegistration> query = new ArrayList<EditorRegistration>();
		switch(orderBy) {
		  case "name":
		    query = registrationRepository.findByEventAndContainSocietyOrderBySocietyName(eventId, societyName);
		    break;
		  case "tables":
			  query = registrationRepository.findByEventAndContainSocietyOrderByTableCount(eventId, societyName);
		    break;
		  case "volunteers":
			  query = registrationRepository.findByEventAndContainSocietyOrderByVolunteerCount(eventId, societyName);
			    break;
		  case "cost":
			  query = registrationRepository.findByEventAndContainSocietyOrderByCost(eventId, societyName);
			    break;
		  default:
		}		
		this.mav.addObject("registrationsResult", query);   
	}
	
	public void validateRegistration(Long editorRegId) {
		if (registrationRepository.findById(editorRegId).isPresent()) {
			EditorRegistration editorRegistration = registrationRepository.findById(editorRegId).get();
			editorRegistration.setState("validated");
			registrationRepository.save(editorRegistration);
			this.mav.clear();
			this.toggleCard = "PendingRegistrations";
		}
	}
	
	public void unvalidateRegistration(Long editorRegId) {
		if (registrationRepository.findById(editorRegId).isPresent()) {
			EditorRegistration editorRegistration = registrationRepository.findById(editorRegId).get();
			editorRegistration.setState("pending");
			registrationRepository.save(editorRegistration);
			this.mav.clear();
			this.toggleCard = "ValidatedRegistrations";
		}
	}
	
	public void cancelRegistration(Long editorRegId) {
		if (registrationRepository.findById(editorRegId).isPresent()) {
			EditorRegistration editorRegistration = registrationRepository.findById(editorRegId).get();
			editorRegistration.setState("canceled");
			registrationRepository.save(editorRegistration);
		}
	}
	
	public void reactivateRegistration(Long editorRegId) {
		if (registrationRepository.findById(editorRegId).isPresent()) {
			EditorRegistration editorRegistration = registrationRepository.findById(editorRegId).get();
			editorRegistration.setState("pending");
			registrationRepository.save(editorRegistration);
			this.mav.clear();
			this.toggleCard = "CanceledRegistrations";
		}
	}
	
	public void deleteRegistration(Long editorRegId) {
		if (registrationRepository.findById(editorRegId).isPresent()) {
			registrationRepository.deleteById(editorRegId);
			this.mav.clear();
			this.toggleCard = "exit";
		}
	}
	
//Shop Registration Section

	public ModelAndView buildAdminRegistrationsShopView(Long eventId) {
	    Date today = new Date();
			Event event = new Event();
			this.mav.clear();
			this.mav.setViewName("adminShopRegistration");
			this.mav.addObject("events",eventService.returnEvent(today));
			if(eventId == null) {
				event = eventService.findLastEvent();
			}
			else {
				event = eventService.findById(eventId);		
			}
			if (event !=null) {
				
				this.mav.addObject("lastEvent",event);
				this.mav.addObject("shopRegistrationPending",shopRegistrationService.findByEventPending(eventService.findById(event.getId())));
				this.mav.addObject("shopRegistrationList",shopRegistrationService.findByEvent(eventService.findById(event.getId())));
				this.mav.addObject("shopRegistrationValidated",shopRegistrationService.findByEventValidated(eventService.findById(event.getId())));
				this.mav.addObject("shopRegistrationCanceled",shopRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
			}
			this.mav.addObject("page","gestionDesInscriptions/boutiques");
			this.mav.addObject("toggleCard", this.toggleCard);
			String registrationPageToggleCard = "colseAll ValidatedRegistrations";
			if (!registrationPageToggleCard.contains(this.toggleCard));
				this.toggleCard = "PendingRegistrations";
			return this.mav;

		}
	
	
	public ModelAndView buildAdminRegistrationsShopSearch(Long eventId, String societyName) {
		Date today = new Date();
		Event event = new Event();
		this.mav.setViewName("adminShopRegistration");
		this.mav.addObject("events",eventService.returnEvent(today));
		if(eventId == null) {
			event = eventService.findLastEvent();
		}
		else {
			event = eventService.findById(eventId);		
		}
		this.mav.addObject("lastEvent",event);
		this.mav.addObject("shopRegistrationPending",shopRegistrationService.findByEventPending(eventService.findById(event.getId())));
		this.mav.addObject("shopRegistrationList",shopRegistrationService.findByEvent(eventService.findById(event.getId())));
		this.mav.addObject("shopRegistrationValidated",shopRegistrationService.findByEventValidated(eventService.findById(event.getId())));
		this.mav.addObject("shopRegistrationCanceled",shopRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
		this.mav.addObject("page","gestionDesInscriptions/boutiques");
		this.mav.addObject("societySearched", societyName);
		this.mav.addObject("toggleCard", "SearchRegistrations");
		return this.mav;
	}
	
	public void setToggleforSelectedShopRegistration() {
		toggleCard="PendingRegistrations";
		
	}
	
	  
	public void setShopRegistrationsResults(Long eventId, String societyName, String orderBy) {
		this.mav.clear();
		List<ShopRegistration> query = new ArrayList<ShopRegistration>();
		switch(orderBy) {
		  case "name":
		    query = shopRegistrationRepository.findByEventAndContainSocietyOrderByName(eventId, societyName);
		    break;
		  case "tables":
			  query = shopRegistrationRepository.findByEventAndContainSocietyOrderByTableCount(eventId, societyName);
		    break;
		}		
		this.mav.addObject("shopRegistrationsResult", query);  		
	}
	
	public void validateShopRegistration(Long shopRegId) {
		ShopRegistration shopRegistration = shopRegistrationRepository.findById(shopRegId).get();
		shopRegistration.setState("validated");
		shopRegistrationRepository.save(shopRegistration);
		this.mav.clear();
		this.toggleCard = "PendingRegistrations";		
	}
	
	public void unvalidateShopRegistration(Long shopRegId) {
		if (shopRegistrationRepository.findById(shopRegId).isPresent()) {
			ShopRegistration shopRegistration = shopRegistrationRepository.findById(shopRegId).get();
			shopRegistration.setState("pending");
			shopRegistrationRepository.save(shopRegistration);
			this.mav.clear();
			this.toggleCard = "ValidatedRegistrations";
		}
	}
	
	public void cancelShopRegistration(Long shopRegId) {
		if (shopRegistrationRepository.findById(shopRegId).isPresent()) {
			ShopRegistration shopRegistration = shopRegistrationRepository.findById(shopRegId).get();
			shopRegistration.setState("canceled");
			shopRegistrationRepository.save(shopRegistration);
		}
	}
	
	public void reactivateShopRegistration(Long shopRegId) {
		if (shopRegistrationRepository.findById(shopRegId).isPresent()) {
			ShopRegistration shopRegistration = shopRegistrationRepository.findById(shopRegId).get();
			shopRegistration.setState("pending");
			shopRegistrationRepository.save(shopRegistration);
			this.mav.clear();
			this.toggleCard = "CanceledRegistrations";
		}
	}
	
	public void deleteShopRegistration(Long shopRegId) {
		if (shopRegistrationRepository.findById(shopRegId).isPresent()) {
			shopRegistrationRepository.deleteById(shopRegId);
			this.mav.clear();
			this.toggleCard = "exit";
		}
	}
	


	public ModelAndView buildShopRegistrationModifView(Long registrationId, Optional<String> toastMsg) {
		
		ShopRegistration shopRegistration = shopRegistrationService.findShopRegById(registrationId);
		if (toastMsg.isPresent()) {
			this.mav.addObject("toastMsg", toastMsg.get());
		}
		this.mav.addObject("agents", shopRegistration.getAgents());
		this.mav.addObject("registration", shopRegistration);
		this.mav.setViewName("adminShopRegistrationModif");
		return this.mav;
	}
  


//Event Section
	
	public void createEvent(Event event) {
		eventRepository.save(event);
		toggleCard = "updateEvent";
	}
    

	public void selectEventById(Long eventId) {
		if (eventRepository.findById(eventId).isPresent()) {
			this.mav.clear();
			Event selectedEvent = eventRepository.findById(eventId).get();
			this.mav.addObject("selectedEvent", selectedEvent);
			toggleCard = "updateEvent";
		}
	}
    

	public void updateEvent(Event event) {
		if (event.getId() != null) {
			Event originalEvent = eventRepository.findById(event.getId()).get();
			if (event.getName() != null)
				originalEvent.setName(event.getName());
			if (event.getTables() > 0)
				originalEvent.setTables(event.getTables());
			if (event.getDescription() != null)
				originalEvent.setDescription(event.getDescription());
			if (event.getProtozoneTables() > 0)
				originalEvent.setProtozoneTables(event.getProtozoneTables());
			if (event.getMaxTablesPerEditor() > 0)
				originalEvent.setMaxTablesPerEditor(event.getMaxTablesPerEditor());
			if (event.getPricePerTable() > 0)
				originalEvent.setPricePerTable(event.getPricePerTable());
			if (event.getSaleOptionPrice() > 0)
				originalEvent.setSaleOptionPrice(event.getSaleOptionPrice());
			if (event.getDiscount() > 0)
				originalEvent.setDiscount(event.getDiscount());
			if (event.getShopPrice() > 0)
				originalEvent.setShopPrice(event.getShopPrice());
			if (event.getEventBeginningDate() != null)
				originalEvent.setEventBeginningDate(event.getEventBeginningDate());
			if (event.getEventEndingDate() != null)
				originalEvent.setEventEndingDate(event.getEventEndingDate());
			if (event.getEditorsRegistrationBeginDate() != null)
				originalEvent.setEditorsRegistrationBeginDate(event.getEditorsRegistrationBeginDate());
			if (event.getEditorsRegistrationEndDate() != null)
				originalEvent.setEditorsRegistrationEndDate(event.getEditorsRegistrationEndDate());
			if (event.getShopsRegistrationBeginDate() != null)
				originalEvent.setShopsRegistrationBeginDate(event.getShopsRegistrationBeginDate());
			if (event.getShopsRegistrationEndDate() != null)
				originalEvent.setShopsRegistrationEndDate(event.getShopsRegistrationEndDate());
			if (event.getVolunteersRegistrationBeginDate() != null)
				originalEvent.setVolunteersRegistrationBeginDate(event.getVolunteersRegistrationBeginDate());
			if (event.getVolunteersRegistrationEndDate() != null)
				originalEvent.setVolunteersRegistrationEndDate(event.getVolunteersRegistrationEndDate());

			eventRepository.save(originalEvent);
			this.mav.clear();
		}
		toggleCard = "updateEvent";
	}
    

	public void deleteEventById(Long eventId) {
		eventRepository.deleteById(eventId);
		toggleCard = "updateEvent";
	}
    

	public void deleteTaskFromEvent(Long eventTaskId) {
		if (eventTaskRepository.findById(eventTaskId).isPresent())		
			eventTaskRepository.deleteById(eventTaskId);
	}

	public ModelAndView buildAdminRegistrationsView() {
		this.mav.setViewName("adminRegistrations");
		this.mav.addObject("editorRegistrationList",editorRegistrationService.findAllEditorRegistrations());
		this.mav.addObject("page","gestionDesInscriptions");
		return this.mav;
	}	
	
    
	public void addTaskToEvent(Long eventId, Long taskId) {
		if (eventRepository.findById(eventId).isPresent() && taskRepository.findById(taskId).isPresent()) {
			Event event = eventRepository.findById(eventId).get();
			Tasks task = taskRepository.findById(taskId).get();			
			
			List<EventTask> originalEventTasks = eventTaskRepository.findByEventId(eventId);
			List <Tasks> originalTasks = new ArrayList<Tasks>();
			for (EventTask originalEventTask : originalEventTasks) {
				originalTasks.add(originalEventTask.getTask());
			}
			if (!originalTasks.contains(task)) {
				EventTask eventTask = new EventTask();
				eventTask.setEvent(event);
				eventTask.setTask(task);				
				eventTaskRepository.save(eventTask);
				event.setEventTasks(eventTaskRepository.findByEventId(eventId));
				eventRepository.save(event);
			}
		}
	}
    

	public void createTask(Tasks task) {
		taskRepository.save(task);
		toggleCard = "task";
	}
    

	public void selectTaskById(Long taskId) {
		if (taskRepository.findById(taskId).isPresent()) {
			this.mav.addObject("selectedTask", taskRepository.findById(taskId).get());
			toggleCard = "task";
		}
	}
    

	public void updateTask(Tasks task) {
		Tasks originalTask = taskRepository.findById(task.getId()).get();
		if (task.getName() != null) {
			originalTask.setName(task.getName());
		}
		taskRepository.save(originalTask);
		this.mav.clear();
		toggleCard = "task";
  }
    

	public void deleteTaskById(Long taskId) {
		List <EventTask> eventTasksToDelete = eventTaskRepository.findByTaskId(taskId);
		List<VolunteerRegistration> volunteerRegistrations = volunteerRegistrationRepository.findAll();

		for (VolunteerRegistration volunteerRegitration :volunteerRegistrations) {
			for (Tasks task : volunteerRegitration.getChosenTasks()) {
				if (task.getId() == taskId) {
					volunteerRegitration.getChosenTasks().remove(task);
					volunteerRegistrationRepository.save(volunteerRegitration);
				}
			}
		}
		eventTaskRepository.deleteAll(eventTasksToDelete);
		taskRepository.deleteById(taskId);
		toggleCard = "task";
	}
	
	//timeslot
	
	public void createTimeslot(TimeSlot timeslot) {
		timeslot.setOrdernumber(timeslot.getOrdernumber()-1);
		timeSlotRepository.save(timeslot);
		toggleCard = "timeslot";
				
		List<TimeSlot> timeSlots = timeSlotRepository.findAllByOrderByOrdernumberAsc();
		for (int i = 0 ; i < timeSlots.size(); i++) {
			timeSlots.get(i).setOrdernumber((long) (i+1));
			timeSlotRepository.save(timeSlots.get(i));
		}
	}
    

	public void selectTimeSlotById(Long timeslotId) {
		if (timeSlotRepository.findById(timeslotId).isPresent()) {
			this.mav.addObject("selectedTimeslot", timeSlotRepository.findById(timeslotId).get());
			toggleCard = "timeslot";
		}
	}
    

	public void updateTimeslot(TimeSlot timeslot) {
		TimeSlot originalTimeSlot = timeSlotRepository.findById(timeslot.getId()).get();
		
		if (timeslot.getName() != null) {
			originalTimeSlot.setName(timeslot.getName());
		}
		if (timeslot.getDescription() != null) {
			originalTimeSlot.setDescription(timeslot.getDescription());
		}
		if (timeslot.getOrdernumber() != null) {
			originalTimeSlot.setOrdernumber((timeslot.getOrdernumber() -1l));
			
		}

		timeSlotRepository.save(originalTimeSlot);
		List<TimeSlot> timeSlots = timeSlotRepository.findAllByOrderByOrdernumberAsc();
		for (int i = 0 ; i < timeSlots.size(); i++) {
			timeSlots.get(i).setOrdernumber((long) (i+1));
			timeSlotRepository.save(timeSlots.get(i));
		}
			
		
		this.mav.clear();
		toggleCard = "timeslot";
  }
    

	public void deleteTimeSlotById(Long timeslot) {
		List<VolunteerRegistration> volunteerRegistrations = volunteerRegistrationRepository.findAll();
		List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		for (VolunteerRegistration volunteerRegistration : volunteerRegistrations) {
			timeSlots = volunteerRegistration.getChosenTimeSlot();
			List<TimeSlot> timeSlotsToRemove = new ArrayList<TimeSlot>(volunteerRegistration.getChosenTimeSlot());
			if (timeSlots != null) {
				for (TimeSlot timeslotReg : timeSlots) {
					if (timeslotReg.getId() == timeslot) {
						timeSlotsToRemove.remove(timeslotReg);
					}
				}				
			}
			volunteerRegistration.setChosenTimeSlot(timeSlotsToRemove);
			volunteerRegistrationRepository.save(volunteerRegistration);
		}
		//			//
		timeSlotRepository.deleteById(timeslot);
		toggleCard = "timeslot";
	}
	
	//Update registration
	public ModelAndView buildEditorRegistrationModif(Long registrationId, Optional<String> toastMsg) {
		
		EditorRegistration editorRegistration = editorRegistrationService.findEditorRegById(registrationId);
		if (toastMsg != null && toastMsg.isPresent()) {
			this.mav.addObject("toastMsg", toastMsg.get());
		}
		this.mav.addObject("agents", editorRegistration.getAgents());
		this.mav.addObject("registration", editorRegistration);
		this.mav.setViewName("adminEditorRegistrationModif");
		return this.mav;
	}
	
	
	public ModelAndView updateAgent(Long agentId, Long registrationId) {		
		this.mav.addObject("selectedAgent", agentService.getAgent(agentId));
		this.mav.addObject("registrationId", registrationId);
		return this.mav;
	}
	
	
	
//Protozone Section
	

	public ModelAndView buildAdminRegistrationsProtozoneView(Long eventId) {
	    Date today = new Date();
			Event event = new Event();
			this.mav.clear();
			this.mav.setViewName("adminProtozone");
			this.mav.addObject("events",eventService.returnEvent(today));
			if(eventId == null) {
				event = eventService.findLastEvent();
			}
			else {
				event = eventService.findById(eventId);		
			}
			this.mav.addObject("protozoneRegistrationPending",protozoneRegistrationService.findByEventPending(eventService.findById(event.getId())));
			this.mav.addObject("protozoneRegistrationList",protozoneRegistrationService.findByEvent(eventService.findById(event.getId())));
			this.mav.addObject("protozoneRegistrationValidated",protozoneRegistrationService.findByEventValidated(eventService.findById(event.getId())));
			this.mav.addObject("protozoneRegistrationCanceled",protozoneRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
			this.mav.addObject("lastEvent",event);
			this.mav.addObject("page","adminProtozone");
			this.mav.addObject("toggleCard", toggleCard);
			this.mav.addObject("page","gestionDesInscriptions/protozone");
			this.mav.addObject("toggleCard", this.toggleCard);
			String registrationPageToggleCard = "colseAll ValidatedRegistrations";
			if (!registrationPageToggleCard.contains(this.toggleCard));
				this.toggleCard = "PendingRegistrations";
			return this.mav;

		}
	
	
	public void setProtozoneRegistrationsResults(Long eventId, String userName) {
		this.mav.clear();
		
		Set <ProtozoneRegistration> protozoneRegs = new HashSet<ProtozoneRegistration>();
		protozoneRegs.addAll(protozoneRegistrationRepository.findByEventAndContainFirstname(eventId, userName));
		protozoneRegs.addAll(protozoneRegistrationRepository.findByEventAndContainLastname(eventId, userName));
		List <ProtozoneRegistration> foundedUsers = new ArrayList<ProtozoneRegistration>(protozoneRegs);
		Collections.sort(foundedUsers, Comparator.comparingLong(ProtozoneRegistration::getId));
		    
		this.mav.addObject("protozoneRegistrationsResult", foundedUsers);
		
	}


	public ModelAndView buildAdminRegistrationsProtozoneSearch(Long eventId, String userName) {
		Date today = new Date();
		Event event = new Event();
		this.mav.setViewName("adminProtozone");
		this.mav.addObject("events",eventService.returnEvent(today));
		if(eventId == null) {
			event = eventService.findLastEvent();
		}
		else {
			event = eventService.findById(eventId);		
		}
		this.mav.addObject("protozoneRegistrationPending",protozoneRegistrationService.findByEventPending(eventService.findById(event.getId())));
		this.mav.addObject("protozoneRegistrationList",protozoneRegistrationService.findByEvent(eventService.findById(event.getId())));
		this.mav.addObject("protozoneRegistrationValidated",protozoneRegistrationService.findByEventValidated(eventService.findById(event.getId())));
		this.mav.addObject("protozoneRegistrationCanceled",protozoneRegistrationService.findByEventCanceled(eventService.findById(event.getId())));
		this.mav.addObject("lastEvent",event);
		this.mav.addObject("page","adminProtozone");
		this.mav.addObject("page","gestionDesInscriptions/protozone");
		this.mav.addObject("toggleCard", "SearchRegistrations");
		this.mav.addObject("protozoneSearched", userName);
		String registrationPageToggleCard = "colseAll ValidatedRegistrations";
		if (!registrationPageToggleCard.contains(this.toggleCard));
			this.toggleCard = "PendingRegistrations";
		return this.mav;
	}


	public void validateProtozoneRegistration(Long protozoneRegId) {
		if (protozoneRegistrationRepository.findById(protozoneRegId).isPresent()) {
			ProtozoneRegistration protozoneRegistration = protozoneRegistrationRepository.findById(protozoneRegId).get();
			protozoneRegistration.setState("validated");
			protozoneRegistrationRepository.save(protozoneRegistration);
			this.mav.clear();
			this.toggleCard = "PendingRegistrations";
		}
		
	}

	
	public void unvalidateProtozoneRegistration(Long protozoneRegId) {
		if (protozoneRegistrationRepository.findById(protozoneRegId).isPresent()) {
			ProtozoneRegistration protozoneRegistration = protozoneRegistrationRepository.findById(protozoneRegId).get();
			protozoneRegistration.setState("pending");
			protozoneRegistrationRepository.save(protozoneRegistration);
			this.mav.clear();
			this.toggleCard = "ValidatedRegistrations";
		}
	}
	
	

	public void cancelProtozoneRegistration(Long protozoneRegId) {
		if (protozoneRegistrationRepository.findById(protozoneRegId).isPresent()) {
			ProtozoneRegistration protozoneRegistration = protozoneRegistrationRepository.findById(protozoneRegId).get();
			protozoneRegistration.setState("canceled");
			protozoneRegistrationRepository.save(protozoneRegistration);
		}
		
	}


	public void reactivateProtozoneRegistration(Long protozoneRegId) {
		if (protozoneRegistrationRepository.findById(protozoneRegId).isPresent()) {
			ProtozoneRegistration protozoneRegistration = protozoneRegistrationRepository.findById(protozoneRegId).get();
			protozoneRegistration.setState("pending");
			protozoneRegistrationRepository.save(protozoneRegistration);
			this.mav.clear();
			this.toggleCard = "CanceledRegistrations";
		}
	}


	public void deleteProtozoneRegistration(Long protozoneRegId) {
		if (protozoneRegistrationRepository.findById(protozoneRegId).isPresent()) {
			protozoneRegistrationRepository.deleteById(protozoneRegId);
			this.mav.clear();
			this.toggleCard = "exit";
		}
		
	}
	
	
	public ModelAndView buildProtozoneRegistrationModifView(Long registrationId, Optional<String> toastMsg) {
		
		ProtozoneRegistration protozoneRegistration = protozoneRegistrationService.findProtozoneById(registrationId);
		if (toastMsg.isPresent()) {
			this.mav.addObject("toastMsg", toastMsg.get());
		}
		this.mav.addObject("registration", protozoneRegistration);
		this.mav.setViewName("adminProtozoneRegistrationModif");
		return this.mav;
	}
		
	
//Volunteer Section
	
	public void submitVolunteerSchedule(Long volunteerRegistrationId, Long eventTaskId, Long timeSlotId) {
		
		EventTask eventTask = new EventTask();
		eventTask = eventTaskRepository.getOne(eventTaskId);
		TimeSlot timeSlot = new TimeSlot();
		timeSlot = timeSlotRepository.getOne(timeSlotId);
		VolunteerRegistration volunteerRegistration = new VolunteerRegistration();
		volunteerRegistration = volunteerRegistrationRepository.findById(volunteerRegistrationId).get();
		List <VolunteerRegistration> volunteerRegistrations = new ArrayList<VolunteerRegistration>();
		volunteerRegistrations.add(volunteerRegistration);
		Planning planning = new Planning();
		planning.setVolunteerRegistration(volunteerRegistrations);
		planning.setTaskName(eventTask.getTask().getName());
		planning.setTimeSlotName(timeSlot.getName());
		planning.setTimeSlotDescription(timeSlot.getDescription());

		List <Planning> plannings = volunteerRegistration.getPlanning();
		Planning savedPlanning = planningRepository.save(planning);
		plannings.add(savedPlanning);
		volunteerRegistration.setPlanning(plannings);
		volunteerRegistration.setState("validated");
		volunteerRegistrationRepository.save(volunteerRegistration);
	}
	

	public void deleteVolunteerschedule(Long planningId) {
		
		planningRepository.deleteById(planningId);	
	}
	
	public void updateSchedule(Long registrationId, Long planningId) {
		VolunteerRegistration volunteerRegistration = volunteerRegistrationRepository.getOne(registrationId);
		List<Planning> plannings = volunteerRegistration.getPlanning();
		for (int i = 0; i<plannings.size(); i++){
			if (plannings.get(i).getId() == planningId) {
				plannings.remove(i);
			}
		}
		volunteerRegistration.setPlanning(plannings);
		volunteerRegistrationRepository.save(volunteerRegistration);
		planningRepository.deleteById(planningId);
	}
	
	public void deleteVolunteerRegistration(Long volunteerRegId) {
		volunteerRegistrationRepository.deleteById(volunteerRegId);
		this.mav.clear();
		this.toggleCard = "exit";
	}
	
	public ModelAndView buildVolunteerRegistrationModif(Long registrationId, Optional<String> toastMsg) {
		
		VolunteerRegistration volunteerRegistration = volunteerRegistrationRepository.getOne(registrationId);
		if (toastMsg.isPresent()) {
			this.mav.addObject("toastMsg", toastMsg.get());
		}
		this.mav.addObject("registration", volunteerRegistration);		
		this.mav.addObject("eventTasks", volunteerRegistration.getEvent().getEventTasks());
		this.mav.addObject("timeSlots", timeSlotRepository.findAll());
		this.mav.setViewName("adminVolunteerRegistrationModif");
		return this.mav;
	}
	
	
//Account Section
	
	public void deleteAccount(Long accountId, String toggleCard) {
		User user = userRepository.getOne(accountId);
		String userRole = user.getUserRole().getWording();
		if (userRole.equals("VOLUNTEER")) {
			List <VolunteerRegistration> volunteerRegistrations = volunteerRegistrationRepository.findByUser(user);
	        for (VolunteerRegistration volunteerRegistration : volunteerRegistrations) {
	        	volunteerRegistrationRepository.deleteById(volunteerRegistration.getId());
	        }   
		} else if (userRole.equals("PROTOZONE")) {
			List <ProtozoneRegistration> protozoneRegistrations = protozoneRegistrationRepository.findByUser(user);
			for (ProtozoneRegistration protozoneRegistration : protozoneRegistrations) {
				protozoneRegistrationRepository.deleteById(protozoneRegistration.getId());
			}
		}		
		userRepository.deleteById(accountId);
		this.toggleCard = toggleCard;		
	}
    
	
	public void createAccount(User user) {	
		if (user.getPassword() != null && user.getEmail() != null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setAccountActive(true);
			userRepository.save(user);
			this.toggleCard = user.getUserRole().getWording().toLowerCase();
		}			
	}
    
	
	public void searchAccountByUserName(String userName) {
		Set <User> foundedUsers = new HashSet<User>();
		foundedUsers.addAll(userRepository.findByFirstnameContaining(userName));
		foundedUsers.addAll(userRepository.findByLastnameContaining(userName));
		List <Society> foundedSociety = new ArrayList<Society>();
		foundedSociety.addAll(societyRepository.findByNameContaining(userName));
		for (Society society : foundedSociety) {
			foundedUsers.addAll(userRepository.findBySociety(society));
		}
		
		if (!foundedUsers.isEmpty()) {
			List <User> distinctUsers = new ArrayList<User>(foundedUsers);
			Collections.sort(distinctUsers, Comparator.comparingLong(User::getId));
			this.mav.addObject("foundedUsers", distinctUsers);
		} else {
			this.mav.addObject("foundedUsers", "none");
		}		
		this.mav.addObject("savedSearch", userName);
		this.toggleCard = "search";
	}

	
	public void selectAccountById(Long accountId) {
		if (userRepository.findById(accountId).isPresent()) {
			this.mav.clear();
			this.mav.addObject("selectedAccount", userRepository.findById(accountId).get());
			this.toggleCard = "edit";			
		}
	}
	
	
	public void updateAccount(User user, String toggleCard) {
		if (user.getId() != null) {
			userService.updateUser(user);		
			this.mav.addObject("selectedAccount", null);
			this.toggleCard = toggleCard;			
		}
	}
}