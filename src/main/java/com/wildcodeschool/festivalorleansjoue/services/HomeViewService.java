package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.entity.TimeSlot;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.TimeSlotRepository;

@Service
public class HomeViewService {

	@Autowired
	private EditorRegistrationService registrationService;
	@Autowired
	private VolunteerRegistrationService VolunteerRegistrationService;
	@Autowired
	ProtozoneRegistrationService protozoneRegistrationService;
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private NavbarLinksService navbarLinks;
	@Autowired
	private GameService gameService;
	@Autowired
	private AgentService agentService;	
	private ModelAndView model = new ModelAndView();
	@Autowired
	TimeSlotRepository timeSlotRepository;
	

	public HomeViewService() {

	}
	

	public ModelAndView getModel() {

		return this.model;
	}
	

	public void setModel(ModelAndView model) {

		this.model = model;
	}
	

	public ModelAndView buildBasicView(String route, User connectedUser) {
		
		this.model.setViewName(route);
		this.navbarLinks.setCurrentPage(route);
		this.model.addObject("connectedUser", connectedUser);
		this.model.addObject("navbarLinks", navbarLinks);
		return this.model;
	}


	public void buildHomeView(String route, Optional<String> toastMsg) {

		Date today = new Date();
		this.model = new ModelAndView(route);
		List<Event> events = new ArrayList<>();
		events = eventService.returnEvent(today);
		User connectedUser = userService.returnConnectedUser();
		eventService.setMessage(connectedUser, events, today);
		if (toastMsg.isPresent()) {
			this.model.addObject("toastMsg", (toastMsg.get()));
		} else if (!connectedUser.isCompletedUserInfos()) {
			this.model.addObject("toastMsg", "userInfosNotCompleted");
		}
		List<EventCardHomeView> eventCardModels = new ArrayList<>();
		switch(connectedUser.getUserRole().getWording()) {
		case "EDITOR":
			Society userSociety = connectedUser.getSociety();
			if (userSociety != null) {
				List<EditorRegistration> registrationList = userSociety.getRegList();
				List<EditorRegistration> registrationListResult = new ArrayList<EditorRegistration>();
				if (registrationList.size() != 0) {
					List<Event> eventsSubscribed = new ArrayList<>();
					for (EditorRegistration reg : registrationList) {
						eventsSubscribed.add(reg.getEvent());
						for (Event event : events) {
							if (event.getId() == reg.getEvent().getId()) {
								registrationListResult.add(reg);
							}
						}
					}
					for (Event event : events) {
						if (eventsSubscribed.contains(event)) {
							eventCardModels.add(new EventCardHomeView(event, false, true));
						} else {
							eventCardModels.add(new EventCardHomeView(event, true, false));
						}
					}

					this.model.addObject("registrations", registrationListResult);
				} else {
					for (int i = 0; i < events.size(); i++) {
						eventCardModels.add(new EventCardHomeView(events.get(i), true, false));
					}
				}
			} else {
				for (int i = 0; i < events.size(); i++) {
					eventCardModels.add(new EventCardHomeView(events.get(i), true, false));
				}
			}
			break;
		case "SHOP":
			Society userShopSociety = connectedUser.getSociety();
			if (userShopSociety != null) {
				List<ShopRegistration> registrationList = userShopSociety.getShopRegList();
				List<ShopRegistration> registrationListResult = new ArrayList<ShopRegistration>();
				if (registrationList.size() != 0) {
					List<Event> eventsSubscribed = new ArrayList<>();
					for (ShopRegistration reg : registrationList) {
						eventsSubscribed.add(reg.getEvent());
						for (Event event : events) {
							if (event.getId() == reg.getEvent().getId()) {
								registrationListResult.add(reg);
							}
						}
					}
					for (Event event : events) {
						if (eventsSubscribed.contains(event)) {
							eventCardModels.add(new EventCardHomeView(event, false, true));
						} else {
							eventCardModels.add(new EventCardHomeView(event, true, false));
						}
					}
					this.model.addObject("shopRegistrations", registrationListResult);
				} else {
					for (int i = 0; i < events.size(); i++) {
						eventCardModels.add(new EventCardHomeView(events.get(i), true, false));
					}
				}
			} else {
				for (int i = 0; i < events.size(); i++) {
					eventCardModels.add(new EventCardHomeView(events.get(i), true, false));
				}
			}
			break;
		case "VOLUNTEER":
			List<VolunteerRegistration> registrationList = VolunteerRegistrationService.findVolunteerRegByUser(connectedUser);
			List<VolunteerRegistration> registrationListResult = new ArrayList<VolunteerRegistration>();
			List<Event> eventsSubscribed = new ArrayList<>();
			for (VolunteerRegistration reg : registrationList) {
				eventsSubscribed.add(reg.getEvent());
				for (Event event : events) {
					if (event.getId() == reg.getEvent().getId()) {
						registrationListResult.add(reg);
					}
				}
			}
			for (Event event : events) {
				if (eventsSubscribed.contains(event)) {
					eventCardModels.add(new EventCardHomeView(event, false, true));
				} else {
					eventCardModels.add(new EventCardHomeView(event, true, false));
				}
			}
			this.model.addObject("volunteerReg", registrationListResult);		
			break;
		case "PROTOZONE":
			List<ProtozoneRegistration> ProtoRegistrationList = protozoneRegistrationService.findProtozoneRegistrationByUser(connectedUser);
			List<ProtozoneRegistration> ProRegistrationListResult = new ArrayList<ProtozoneRegistration>();
			List<Event> eventsProtoSubscribed = new ArrayList<>();
			for (ProtozoneRegistration reg : ProtoRegistrationList) {
				eventsProtoSubscribed.add(reg.getEvent());
				for (Event event : events) {
					if (event.getId() == reg.getEvent().getId()) {
						ProRegistrationListResult.add(reg);
					}
				}
			}
			for (Event event : events) {
				if (eventsProtoSubscribed.contains(event)) {
					eventCardModels.add(new EventCardHomeView(event, false, true));
				} else {
					eventCardModels.add(new EventCardHomeView(event, true, false));
				}
			}
			this.model.addObject("protozoneReg", ProRegistrationListResult);
		}
		this.model.addObject("event", eventCardModels);
		buildBasicView(route, connectedUser);
	}
	

	public void buildSubscribeEditorModificationView(String route, Long registrationId, Optional<String> toastMsg) {

		this.model = new ModelAndView(route);
		EditorRegistration editorRegistration = registrationService.findEditorRegById(registrationId);
		User connectedUser = userService.returnConnectedUser();
		this.model.addObject("registration", editorRegistration);
		this.model.addObject("event", editorRegistration.getEvent());
		this.model.addObject("gamesRegistration", gameService.findByRegistration(editorRegistration));
		this.model.addObject("agentsRegistration", agentService.findAgentByRegistration(editorRegistration));
		this.model.addObject("gamesSocietyOnly", gameService.findByRegistrationNotSociety(connectedUser.getSociety(), editorRegistration));
		this.model.addObject("society", connectedUser.getSociety());
		this.model.addObject("games", gameService.ReturnGamesBySociety(connectedUser.getSociety()));
		if (toastMsg.isPresent()) {
			this.model.addObject("toastMsg", (toastMsg.get()));
		}
		buildBasicView(route, connectedUser);
	}

	
	public void buildSubscribeShopModificationView(String route, Long registrationId, Optional<String> toastMsg) {

		this.model = new ModelAndView(route);
		ShopRegistration shopRegistration = registrationService.findShopById(registrationId);
		User connectedUser = userService.returnConnectedUser();
		this.model.addObject("registration", shopRegistration);
		this.model.addObject("event", shopRegistration.getEvent());
		this.model.addObject("agentsRegistration", agentService.findAgentByShopRegistrations(shopRegistration));
		this.model.addObject("society", connectedUser.getSociety());
		this.model.addObject("agents", agentService.findAgentBySociety(connectedUser.getSociety()));		
		if (toastMsg.isPresent()) {
			this.model.addObject("toastMsg", (toastMsg.get()));
		}
		buildBasicView(route, connectedUser);
	}

	
	public void buildSubscribeVolunteerModificationView(String route, Long registrationId, Optional<String> toastMsg) {

		this.model = new ModelAndView(route);
		VolunteerRegistration volunteerRegistration = VolunteerRegistrationService.findVolunteerById(registrationId);
		List<TimeSlot> timeSlot = timeSlotRepository.findAllByOrderByOrdernumberAsc();
		User connectedUser = userService.returnConnectedUser();
		this.model.addObject("registration", volunteerRegistration);
		this.model.addObject("event", volunteerRegistration.getEvent());
		this.model.addObject("timeSlot",timeSlot);
		
		buildBasicView(route, connectedUser);
	}


	public void buildSubscribeProtozoneModificationView(String route, Long registrationId, Optional<String> toastMsg) {
		this.model = new ModelAndView(route);
		ProtozoneRegistration protozoneRegistration = protozoneRegistrationService.findProtozoneById(registrationId);
		User connectedUser = userService.returnConnectedUser();
		this.model.addObject("registration", protozoneRegistration);
		this.model.addObject("event", protozoneRegistration.getEvent());
		this.model.addObject("comments",protozoneRegistration.getComments());
		
		buildBasicView(route, connectedUser);
		
	}

}
