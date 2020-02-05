package com.wildcodeschool.festivalorleansjoue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.services.VolunteerRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;



@RestController
public class VolunteerRegistrationController {
		
	@Autowired
	private VolunteerRegistrationService registrationService;	

	
	@PostMapping(value = "/cancelVolunteerRegistration")
	public ModelAndView deleteVolunteerRegistration(@RequestParam Long volunteerRegId) {
		
		VolunteerRegistration canceledReg = registrationService.findVolunteerById(volunteerRegId);
		/*canceledReg.setState("canceled");
		registrationService.saveVolunteerReg(canceledReg);
		*/
		registrationService.deleteVolunteerRegistration(volunteerRegId);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "delete");
		return new ModelAndView("redirect:/accueil", model);
	}
	
	
	@PostMapping("/updateVolunteerRegistration")
	public ModelAndView updateVolunteerRegistration(@ModelAttribute VolunteerRegistration volunteerRegistration) {

		registrationService.updateVolunteerRegistrationService(volunteerRegistration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "update");
		model.addAttribute("registrationId", volunteerRegistration.getId());

		return new ModelAndView("redirect:/accueil#inscription", model);
	}
}
