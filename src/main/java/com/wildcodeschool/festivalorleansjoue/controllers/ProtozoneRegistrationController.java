package com.wildcodeschool.festivalorleansjoue.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.services.ProtozoneRegistrationService;



@RestController
public class ProtozoneRegistrationController {
		
	@Autowired
	private ProtozoneRegistrationService protozoneRegistrationService;	

	
	@PostMapping(value = "cancelProtozoneRegistration")
	public ModelAndView deleteProtozoneRegistration(@RequestParam Long protozoneRegistrationId) {
		
		ProtozoneRegistration canceledReg = protozoneRegistrationService.findProtozoneById(protozoneRegistrationId);
		canceledReg.setState("canceled");
		protozoneRegistrationService.saveProtozoneRegistration(canceledReg);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "canceled");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@PostMapping(value = "updateProtozoneRegistration")
	public ModelAndView updateProtozoneRegistration(@ModelAttribute ProtozoneRegistration registration, @RequestParam(required = false) Long registrationId) {

		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "update");
		protozoneRegistrationService.updateProtozoneRegistrationService(registration);					
		model.addAttribute("registrationId", registration.getId());
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
}
