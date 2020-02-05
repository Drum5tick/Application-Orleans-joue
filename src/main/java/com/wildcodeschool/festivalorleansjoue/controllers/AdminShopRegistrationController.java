package com.wildcodeschool.festivalorleansjoue.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.services.AdminService;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.ShopRegistrationService;


@RestController
public class AdminShopRegistrationController {
		
	@Autowired
	private EditorRegistrationService registrationService;	
	
	@Autowired 
	private AdminService adminService;
	
	@Autowired
	private ShopRegistrationService shopRegistrationService;
	
	//Shop Registration Section


	@RequestMapping(value = "admin/submitAdminForShopRegistration" , method = RequestMethod.POST)
	public ModelAndView submitAdminForShopRegistration(@RequestParam(value="eventId", required=false) Long eventId) {
		adminService.setToggleforSelectedRegistration();
		ModelMap model = new ModelMap();
		model.addAttribute("eventId",eventId);
		return new ModelAndView("redirect:/admin/gestionDesInscriptions/boutiques", model);
	}
	
	
	@RequestMapping(value = "admin/researchForShopRegistrations" , method = RequestMethod.POST)
	public ModelAndView researchForShopRegistration(@RequestParam(value="societyName", required=true) String societyName,
												@RequestParam(value="eventId", required=false) Long eventId,
												@RequestParam(required=false) String orderBy							
												) {

		adminService.setShopRegistrationsResults(eventId, societyName, orderBy);
		return adminService.buildAdminRegistrationsShopSearch(eventId, societyName);
	}

	
	@PostMapping("admin/validateShopRegistration")
	public ModelAndView validateShopRegistration(@RequestParam Long eventId, @RequestParam Long shopRegId) {
		adminService.validateShopRegistration(shopRegId);
		return adminService.buildAdminRegistrationsShopView(eventId);
	}
	
	
	@PostMapping("admin/unvalidateShopRegistration")
	public ModelAndView unvalidateShopRegistration(@RequestParam Long eventId, @RequestParam Long shopRegId) {
		adminService.unvalidateShopRegistration(shopRegId);
		return adminService.buildAdminRegistrationsShopView(eventId);
	}
	
	
	@PostMapping("admin/cancelShopRegistration")
	public ModelAndView cancelShopRegistration(@RequestParam Long eventId, @RequestParam Long shopRegId) {
		adminService.cancelShopRegistration(shopRegId);
		return adminService.buildAdminRegistrationsShopView(eventId);
	}
	
	
	@PostMapping("admin/reactivateShopRegistration")
	public ModelAndView reactivateShopRegistration(@RequestParam Long eventId, @RequestParam Long shopRegId) {
		adminService.reactivateShopRegistration(shopRegId);
		return adminService.buildAdminRegistrationsShopView(eventId);
	}
	
	
	@PostMapping("admin/deleteShopRegistration")
	public ModelAndView deleteShopRegistration(@RequestParam Long eventId, @RequestParam Long shopRegId) {
		adminService.deleteShopRegistration(shopRegId);
		return adminService.buildAdminRegistrationsShopView(eventId);
	}
	
	@RequestMapping(value = "admin/editerInscriptionBoutique", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView showShopRegistrationEditPage(@RequestParam Long registrationId, Optional<String> toastMsg) {

		adminService.getMav().clear();
		return adminService.buildShopRegistrationModifView(registrationId, toastMsg);
	}
	
	@PostMapping("admin/saveModifiedShopReg")
	public ModelAndView saveShopRegistrationModif(@ModelAttribute ShopRegistration registration, Long registrationId) {

		registration.setId(registrationId);
		shopRegistrationService.updateShopRegistration(registration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "accountModifSaved");
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/admin/editerInscriptionBoutique", model);
	}
	
	@GetMapping("admin/editerCollaborateurBoutique")
	public ModelAndView shopAgentEdition(@RequestParam Long agentRegistrationId, Long registrationId,
			Optional<String> toastMsg) {

		adminService.getMav().clear();
		adminService.updateAgent(agentRegistrationId, registrationId);
		return adminService.buildShopRegistrationModifView(registrationId, toastMsg);
	}

}
