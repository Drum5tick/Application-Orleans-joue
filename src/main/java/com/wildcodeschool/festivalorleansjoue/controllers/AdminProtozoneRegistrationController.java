package com.wildcodeschool.festivalorleansjoue.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.services.AdminService;
import com.wildcodeschool.festivalorleansjoue.services.ProtozoneRegistrationService;


@RestController
public class AdminProtozoneRegistrationController {
		
	@Autowired
	private ProtozoneRegistrationService protozoneRegistrationService;	
	
	@Autowired 
	private AdminService adminService;
	
	
	@GetMapping("admin/gestionProtozone")
	public ModelAndView adminProtozone(@RequestParam (name="eventId", required=false) Long eventId, String toggleCard) {
		
		return adminService.buildAdminProtozoneView(eventId, toggleCard);
	}
	
	
	@RequestMapping(value = "admin/submitAdminForProtozone" , method = RequestMethod.POST)
	public ModelAndView submitAdminForProtozone(@RequestParam(value="eventId", required=false) Long eventId) {
		
		adminService.setToggleforSelectedRegistration();
		ModelMap model = new ModelMap();
		model.addAttribute("eventId",eventId);
		return new ModelAndView("redirect:/admin/gestionProtozone", model);
	}
	
		
	@RequestMapping(value = "admin/submitAdminForProtozoneRegistration" , method = RequestMethod.POST)
	public ModelAndView submitAdminForProtozoneRegistration(@RequestParam(value="eventId", required=false) Long eventId) {
		adminService.setToggleforSelectedRegistration();
		ModelMap model = new ModelMap();
		model.addAttribute("eventId",eventId);
		return new ModelAndView("redirect:/admin/gestionDesInscriptions/protozone", model);
	}
	
	
	@RequestMapping(value = "admin/researchForProtozoneRegistrations" , method = RequestMethod.POST)
	public ModelAndView researchForProtozoneRegistration(@RequestParam(required=true) String userName,
												@RequestParam(value="eventId", required=false) Long eventId						
												) {

		adminService.setProtozoneRegistrationsResults(eventId, userName);
		return adminService.buildAdminRegistrationsProtozoneSearch(eventId, userName);
	}

	
	@PostMapping("admin/validateProtozoneRegistration")
	public ModelAndView validateProtozoneRegistration(@RequestParam Long eventId, @RequestParam Long protozoneRegId) {
		adminService.validateProtozoneRegistration(protozoneRegId);
		return adminService.buildAdminRegistrationsProtozoneView(eventId);
	}
	
	@PostMapping(value = "admin/updateProtozoneRegistration")
	public ModelAndView adminUpdateProtozoneRegistration(@ModelAttribute ProtozoneRegistration registration, @RequestParam(required = false) Long registrationId) {

		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "update");
			if (registrationId != null)
				registration.setId(registrationId);
			protozoneRegistrationService.updateProtozoneRegistrationService(registration);
			return new ModelAndView("redirect:/admin/gestionDesInscriptions/protozone", model);
	}
	
	
	@PostMapping("admin/unvalidateProtozoneRegistration")
	public ModelAndView unvalidateProtozoneRegistration(@RequestParam Long eventId, @RequestParam Long protozoneRegId) {
		adminService.unvalidateProtozoneRegistration(protozoneRegId);
		return adminService.buildAdminRegistrationsProtozoneView(eventId);
	}
	
	@PostMapping("admin/adminCancelProtozoneRegistration")
	public ModelAndView cancelProtozoneRegistration(@RequestParam Long eventId, @RequestParam Long protozoneRegId) {
		adminService.cancelProtozoneRegistration(protozoneRegId);
		return adminService.buildAdminRegistrationsProtozoneView(eventId);
	}
	
	@PostMapping("admin/reactivateProtozoneRegistration")
	public ModelAndView reactivateProtozoneRegistration(@RequestParam Long eventId, @RequestParam Long protozoneRegId) {
		adminService.reactivateProtozoneRegistration(protozoneRegId);
		return adminService.buildAdminRegistrationsProtozoneView(eventId);
	}
	
	@PostMapping("admin/adminDeleteProtozoneRegistration")
	public ModelAndView deleteProtozoneRegistration(@RequestParam Long eventId, @RequestParam Long protozoneRegId) {
		adminService.deleteProtozoneRegistration(protozoneRegId);
		return adminService.buildAdminRegistrationsProtozoneView(eventId);
	}
	
	@RequestMapping(value = "admin/editerInscriptionProtozone", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView showProtozoneRegistrationEditPage(@RequestParam Long registrationId,
			Optional<String> toastMsg) {

		adminService.getMav().clear();
		return adminService.buildProtozoneRegistrationModifView(registrationId, toastMsg);
	}
		
	@PostMapping("admin/adminUpdateProtozoneRegistration")
	public ModelAndView updateProtozoneRegistration(@ModelAttribute ProtozoneRegistration protozoneRegistration) {

		protozoneRegistrationService.updateProtozoneRegistration(protozoneRegistration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "update");
		model.addAttribute("registrationId", protozoneRegistration.getId());

		return new ModelAndView("redirect:/admin/modificationInscriptionEvenementProtozone", model);
	}
}
