package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.TimeSlot;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.TimeSlotRepository;

@Service
public class SubscribeViewService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private HomeViewService homeViewService;
	private ModelAndView mav;
	@Autowired
	TimeSlotRepository timeSlotRepository;
	
	public SubscribeViewService() {
		
	}
	
	
	public ModelAndView getMav() {
		
		return this.mav;
	}
	

	public void setMav(ModelAndView mav) {
		
		this.mav = mav;
	}
	
	
	public ModelAndView buildSubscribeView(String route, Long id) {
		
		Optional<String> toastMsg = Optional.of("alreadyReg");
		Event event = eventService.findById(id);
		List<TimeSlot> timeSlot = timeSlotRepository.findAllByOrderByOrdernumberAsc();
		User connectedUser = userService.returnConnectedUser();
		if (connectedUser.getUserRole().getWording().equals("EDITOR")) {
			List<EditorRegistration> editorRegList = new ArrayList<>();
			editorRegList = event.getEditorRegList();
			if (editorRegList.size() != 0) {
				for (int i = 0 ; i < editorRegList.size() ; i++) {
					if (connectedUser.getSociety().getName().equals(editorRegList.get(i).getSociety().getName())) {
						homeViewService.buildHomeView("home", toastMsg);
						return homeViewService.getModel();
					}
				}
			} 
		}
		this.mav = homeViewService.buildBasicView(route, connectedUser);
		this.mav.addObject("event", event);
		this.mav.addObject("timeSlot", timeSlot);
		return this.mav;
	}
	

}
