package com.wildcodeschool.festivalorleansjoue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.services.FileService;
import com.wildcodeschool.festivalorleansjoue.services.UserAccountService;
import com.wildcodeschool.festivalorleansjoue.services.UserService;

@Controller
public class UserAccountRegController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private FileService fileService;
	
	
	@GetMapping("/login")
	public ModelAndView showLoginPage(ModelAndView model) {
		
		model.setViewName("login");
		return model;
	}
	
	
	@GetMapping("/loginError")
	public ModelAndView showLoginPageErrors(ModelAndView model) {
		String toastMsg = "loginError";
		model.addObject("toastMsg", toastMsg);
		model.setViewName("login");
		return model;
	}
	
	
	@PostMapping("/ajoutUtilisateur")
	public ModelAndView postSignUp(@ModelAttribute User user, @RequestParam(name = "user_role") String userRole, @RequestParam String profilePicture) {
		
		ModelAndView model = new ModelAndView();
		User userExists = userService.findByEmail(user.getEmail());
		String toastMsg = "";
		if (userExists != null) {
			toastMsg = "emailError";
			model.addObject("toastMsg", toastMsg);
			model.setViewName("userRegPage");
			model.addObject("user", userService.createEmptyUser());
			model.addObject("regUserRole", userRole);
		} else {
			user.setProfilePicture(profilePicture);
			user.setAccountActive(true);
			user.setCompletedUserInfos(false);
			userService.registrateUser(user, userRole);
			toastMsg = "userRegSuccessful";
			model.addObject("toastMsg", toastMsg);
			model.setViewName("login");
		}
		return model;
	}
	
	
	@RequestMapping("/typeUtilisateur")
	public ModelAndView showUserTypeChoicePage(ModelAndView model) {
		
		model.setViewName("userTypeChoice");
		return model;
	}
	
	
	@GetMapping("/inscriptionEditeur")
	public ModelAndView showEditorRegPage(ModelAndView model) {
		
		model.setViewName("userRegPage");
		model.addObject("user", userService.createEmptyUser());
		model.addObject("regUserRole", "EDITOR");
		return model;
	}
	
	
	@GetMapping("/inscriptionBoutique")
	public ModelAndView showShopRegPage(ModelAndView model) {
		
		model.setViewName("userRegPage");
		model.addObject("user", userService.createEmptyUser());
		model.addObject("regUserRole", "SHOP");
		return model;
	}
	
	
	@GetMapping("/inscriptionBenevole")
	public ModelAndView showVolunteerRegPage(ModelAndView model) {
		
		model.setViewName("userRegPage");
		model.addObject("user", userService.createEmptyUser());
		model.addObject("regUserRole", "VOLUNTEER");
		return model;
	}
	
	
	@GetMapping("/inscriptionProtozone")
	public ModelAndView showProtozoneRegPage(ModelAndView model) {
		
		model.setViewName("userRegPage");
		model.addObject("user", userService.createEmptyUser());
		model.addObject("regUserRole", "PROTOZONE");
		return model;
	}
	
	
	@GetMapping("/modificationInformationPersonnelle")
	public ModelAndView showUserAccountModifPage(ModelAndView model) {
		
		model = userAccountService.buildAccountModifView();		
		return model;
	}
	
	
	@PostMapping("/userAccountModifSave")
	public ModelAndView saveUserInfos(@ModelAttribute(name = "connectedUser") User user, @RequestParam(required = false) MultipartFile filePicture) {
		if (!filePicture.isEmpty()) {
			user.setProfilePicture("../uploads/pictures/" + fileService.uploadFile(filePicture));
		}
		user.setAccountActive(true);
		user.setCompletedUserInfos(true);
		userService.updateUser(user);
		ModelAndView mav = new ModelAndView("redirect:/accueil#information");
		mav.addObject("toastMsg", "accountModifSaved");
		return mav;
	}
}
	
	
	

