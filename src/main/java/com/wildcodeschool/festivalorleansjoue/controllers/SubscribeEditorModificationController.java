package com.wildcodeschool.festivalorleansjoue.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.services.HomeViewService;

@Controller
public class SubscribeEditorModificationController {

	@Autowired
	HomeViewService homeViewService;

	@RequestMapping(value = "/modificationInscriptionEvenementEditeur", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView subscribeEditorUpdate(@RequestParam Long registrationId, @RequestParam Optional<String> toastMsg) {

		homeViewService.buildSubscribeEditorModificationView("subscribeEditorModification", registrationId, toastMsg);
		return homeViewService.getModel();
	}
}
