package com.wildcodeschool.festivalorleansjoue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.User;

@Service
public class UserAccountService {
	
	@Autowired
	HomeViewService homeViewService;
	@Autowired
	private UserService userService;

	public ModelAndView buildAccountModifView() {
		
		ModelAndView mav = new ModelAndView();
		User connectedUser = userService.returnConnectedUser();
		homeViewService.buildBasicView("userAccountModif", connectedUser);
		mav = homeViewService.getModel();
		return mav;
	}
	
	
}
