package com.wildcodeschool.festivalorleansjoue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.ProtozoneRegistrationService;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.services.SubscribeViewService;
import com.wildcodeschool.festivalorleansjoue.services.VolunteerRegistrationService;


@Controller
public class SubscribeController {
	
	@Autowired
	private SubscribeViewService subscribeViewService;
	@Autowired
	private EditorRegistrationService registrationService;
	@Autowired
	private VolunteerRegistrationService volunteerRegistrationService;
	@Autowired
	private ProtozoneRegistrationService protozoneRegistrationService;

	@PostMapping("/inscriptionEvenement")
	public ModelAndView subscribe(@RequestParam Long id) {
		
		return subscribeViewService.buildSubscribeView("subscribeEvent",id);
	}
	
	
	@PostMapping("/submitEditorRegistration")
	public ModelAndView submitEditorRegistration(@ModelAttribute EditorRegistration editorRegistration, @RequestParam Long userId, @RequestParam Long eventId) {
		
		registrationService.submitEditorRegistration(editorRegistration, userId, eventId);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "ok");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@PostMapping("/submitShopRegistration")
	public ModelAndView submitShopRegistration(@ModelAttribute ShopRegistration shopRegistration, @RequestParam Long userId, @RequestParam Long eventId) {
		
		registrationService.submitShopRegistration(shopRegistration, userId, eventId);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "ok");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@PostMapping("/submitProtozoneRegistration")
	public ModelAndView submitPrtozoneRegistration(@ModelAttribute ProtozoneRegistration protozoneRegistration, @RequestParam Long userId, @RequestParam Long eventId) {
		
		protozoneRegistrationService.submitProtozoneRegistration(protozoneRegistration, userId, eventId);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "ok");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@PostMapping("/submitVolunteerRegistration")
	public ModelAndView submitVolunteerRegistration(@ModelAttribute VolunteerRegistration volunteerRegistration,
													@RequestParam Long eventId, @RequestParam Long userId
													
													)  {
		
		if (volunteerRegistration.getUser()!=null)
			System.out.println("user id" + (volunteerRegistration.getUser()==null));
		
		volunteerRegistrationService.submitVolunteerRegistration(volunteerRegistration, eventId, userId);
		 
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "ok");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@GetMapping("/stopRegistration")
	public ModelAndView stopRegistration() {
		
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "ko");
		return new ModelAndView("redirect:/accueil", model);
	}

}
