package com.wildcodeschool.festivalorleansjoue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.wildcodeschool.festivalorleansjoue.services.AppExceptionService;
import com.wildcodeschool.festivalorleansjoue.services.HomeViewService;
import com.wildcodeschool.festivalorleansjoue.services.UserService;

@ControllerAdvice
public class AppExceptionHandler {
	
	@Autowired
	private HomeViewService homeViewService;
	@Autowired
	private UserService userService;
	
	
    @ExceptionHandler(AppExceptionService.class)
    public ModelAndView handleException(AppExceptionService appExceptionService, RedirectAttributes redirectAttributes) {

        homeViewService.buildBasicView("error", userService.returnConnectedUser());
        ModelAndView mav = homeViewService.getModel();
        mav.addObject("message", appExceptionService.getMsg());
        mav.setViewName("error");
        return mav;
    }
    
    
    @ExceptionHandler(MultipartException.class)
    public ModelAndView handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

    	return handleException(new AppExceptionService(e.getCause().getMessage()), redirectAttributes);
    }
    
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handleError2(UsernameNotFoundException e, RedirectAttributes redirectAttributes) {

    	return handleException(new AppExceptionService(e.getCause().getMessage()), redirectAttributes);
    }
    
}
