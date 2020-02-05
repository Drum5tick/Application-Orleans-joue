package com.wildcodeschool.festivalorleansjoue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.services.ShopRegistrationService;
@Controller
public class ShopController {
	
	@Autowired
	ShopRegistrationService shopRegistrationService;

	@PostMapping("updateShopRegistration")
	public ModelAndView updateShopRegistration(@ModelAttribute ShopRegistration shopRegistration) {

		shopRegistrationService.updateShopRegistration(shopRegistration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "update");
		model.addAttribute("registrationId", shopRegistration.getId());

		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@PostMapping(value = "/cancelShopRegistration")
	public ModelAndView deleteShopRegistration(@RequestParam Long registrationId) {		
		ShopRegistration canceledReg = shopRegistrationService.findShopById(registrationId);
		canceledReg.setState("canceled");
		shopRegistrationService.saveShopReg(canceledReg);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "canceled");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
}
