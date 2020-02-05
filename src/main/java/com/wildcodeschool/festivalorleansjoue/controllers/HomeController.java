package com.wildcodeschool.festivalorleansjoue.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.services.HomeViewService;
import com.wildcodeschool.festivalorleansjoue.services.UserService;

@Controller
public class HomeController {

	@Autowired
	HomeViewService homeViewService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public ModelAndView root() {
		if (userService.returnConnectedUser().getUserRole().getWording().equals("ADMIN"))
			return new ModelAndView("redirect:admin/accueilAdmin");
		return new ModelAndView("redirect:/accueil");
	}
	
	
	@RequestMapping(value = "/accueil", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView homeByRole(@RequestParam Optional<String> toastMsg) {

		homeViewService.buildHomeView("home", toastMsg);  		
		return homeViewService.getModel();
	}
}