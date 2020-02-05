package com.wildcodeschool.festivalorleansjoue.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.festivalorleansjoue.services.ExportService;

@Controller
public class ExportController {

	@Autowired
	ExportService exportService;
	
	@GetMapping("admin/export")
    public void exportCSVController(HttpServletResponse response, @RequestParam Long eventId) throws Exception {
		exportService.exportCSV(response,"recapitulatif_editeurs",eventId);
	}
	
	
	@GetMapping("admin/exportShop")
    public void exportShopCSVController(HttpServletResponse response, @RequestParam Long eventId) throws Exception {
		exportService.exportShopCSV(response,"recapitulatif_boutiques",eventId);
	}
	
	
	@GetMapping("admin/exportVolunteer")
    public void exportVolunteerCSVController(HttpServletResponse response, @RequestParam Long eventId) throws Exception {
		exportService.exportVolunteerCSV(response,"recapitulatif_benevoles",eventId);
	}
	
	
	@GetMapping("admin/exportPlanning")
    public void exportVolunteerPlanningCSVController(HttpServletResponse response, @RequestParam Long eventId) throws Exception {
		exportService.exportVolunteerPlanningCSV(response,"planning",eventId);
	}
	

	@GetMapping("admin/exportProtozone")
    public void exportProtozoneCSV(HttpServletResponse response, @RequestParam Long eventId) throws Exception {
		exportService.exportProtozoneCSV(response,"recapitulatif_protozone",eventId);
	}

}
