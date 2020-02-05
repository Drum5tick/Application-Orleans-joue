package com.wildcodeschool.festivalorleansjoue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;


@RestController
public class RegistrationController {
		
	@Autowired
	private EditorRegistrationService registrationService;	
	
	
	@PostMapping(value = "cancelEditorRegistration")
	public ModelAndView deleteEditorRegistration(@RequestParam Long registrationId) {
		
		EditorRegistration canceledReg = registrationService.findEditorRegById(registrationId);
		canceledReg.setState("canceled");
		registrationService.saveEditorReg(canceledReg);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "canceled");
		return new ModelAndView("redirect:/accueil#inscription", model);
	}
	
	
	@PostMapping(value = "addGameToRegistration")
	public ModelAndView addGameToRegistration(@RequestParam Long registrationId,
											  @RequestParam(name="gameId") Long gameId) {
		
		registrationService.addRegistrationGame(gameId, registrationId);				
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
	    
	    return new ModelAndView("redirect:modificationInscriptionEvenementEditeur", model);
	}


	@PostMapping("updateEditorRegistration")
	public ModelAndView updateEditorRegistration(@ModelAttribute EditorRegistration editorRegistration) {

		registrationService.updateEditorRegistrationService(editorRegistration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "update");
		model.addAttribute("registrationId", editorRegistration.getId());

		return new ModelAndView("redirect:accueil#inscription", model);
	}
}
