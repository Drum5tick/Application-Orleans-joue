package com.wildcodeschool.festivalorleansjoue.controllers;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.services.HomeViewService;
import com.wildcodeschool.festivalorleansjoue.services.UserService;

@Controller
public class ErrorsController implements ErrorController {

	@Autowired
	HomeViewService homeViewService;
	@Autowired 
	UserService userService;
	
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletResponse response) {
		
		String errorMsg = "";
		if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
			errorMsg = "Erreur 404 : page non trouvée.";
		}
		else if(response.getStatus() == HttpStatus.FORBIDDEN.value()) {
			errorMsg = "Erreur 403 : accès interdit.";
		}
		else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			errorMsg = "Erreur 500 : erreur interne serveur.";
		}
		else {
			errorMsg = "Code erreur : " + response.getStatus();
		}
		homeViewService.buildBasicView("error", userService.returnConnectedUser());
		homeViewService.getModel().addObject("message", errorMsg);
		return homeViewService.getModel();
	}
	
	@RequestMapping("/adminError")
	public ModelAndView handleErrorAdmin(HttpServletResponse response) {
		
		String errorMsg = "";
		if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
			errorMsg = "Erreur 404 : page non trouvée.";
		}
		else if(response.getStatus() == HttpStatus.FORBIDDEN.value()) {
			errorMsg = "Erreur 403 : accès interdit.";
		}
		else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			errorMsg = "Erreur 500 : erreur interne serveur.";
		}
		else {
			errorMsg = "Code erreur : " + response.getStatus();
		}
		homeViewService.buildBasicView("error", userService.returnConnectedUser());
		homeViewService.getModel().addObject("message", errorMsg);
		return homeViewService.getModel();
	}
	
	@Override
	public String getErrorPath() {
		
		return "/error";
	}

}
