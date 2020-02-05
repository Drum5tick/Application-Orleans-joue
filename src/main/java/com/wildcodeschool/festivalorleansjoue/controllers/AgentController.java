package com.wildcodeschool.festivalorleansjoue.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.wildcodeschool.festivalorleansjoue.entity.Agent;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.AgentRepository;
import com.wildcodeschool.festivalorleansjoue.repository.RegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.repository.SocietyRepository;
import com.wildcodeschool.festivalorleansjoue.services.AdminService;
import com.wildcodeschool.festivalorleansjoue.services.AgentService;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;

@Controller	
public class AgentController {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private EditorRegistrationService registrationService;
	
	@Autowired
	private SocietyRepository societyRepository;
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private AdminService adminService;

	@PostMapping("/agent")
	public String getAll(Model model) {
		
		model.addAttribute("agent", agentRepository.findAll());
		return "agent";
	}

	
	@PostMapping("/addAgent")
	public ModelAndView postAgent (@ModelAttribute Agent agent) {
		
		agentService.addAgent(agent);
		ModelMap model = new ModelMap();
		model.addAttribute("Agent", agent);
		return new ModelAndView("redirect:/profil", model);
	}
	
	
	@PostMapping("/addRegistrationAgent")
    public ModelAndView postRegistrationAgent(@ModelAttribute Agent agent, Long registrationId, Long societyId, Long tablesQuantity) {
		agent.setSociety(societyRepository.getOne(societyId));
		agentService.addAgent(agent);
		registrationService.addRegistrationAgent(agent, registrationId, tablesQuantity);
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/modificationInscriptionEvenementEditeur", model);
    }
	
	
	@PostMapping("/saveModifiedEditorAgent")
	public ModelAndView saveModifiedAgent(@ModelAttribute Agent agent, Long registrationId, Long agentId) {
		
		agent.setId(agentId);
		agentService.updateAgent(agent);
		adminService.getMav().clear();
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/admin/editerInscription#headingAgent", model);	
	}
	
	
	@PostMapping("/saveModifiedShopAgent")
	public ModelAndView saveModifiedShopAgent(@ModelAttribute Agent agent, Long registrationId, Long agentId) {
		
		agent.setId(agentId);
		agentService.updateAgent(agent);
		adminService.getMav().clear();
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/admin/editerInscriptionBoutique", model);	
	}
	
	
	@RequestMapping(value = "/deleteRegistrationAgent", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteAgent(@RequestParam Long agentRegistrationId, Long registrationId, @RequestParam Optional<Boolean> adminRequest, Double tablesQuantity) {
		
		Agent agent = agentRepository.getOne(agentRegistrationId);
		registrationService.deleteRegistrationAgent(agent, registrationId, tablesQuantity);
		agentService.deleteAgent(agent);
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
		if (adminRequest.isPresent()) {
			return new ModelAndView("redirect:/admin/editerInscription#headingAgent", model);
		}
		return new ModelAndView("redirect:/modificationInscriptionEvenementEditeur", model);
    }
	
	
	@PostMapping("/addShopRegistrationAgent")
    public ModelAndView postShopRegistrationAgent(@ModelAttribute Agent agent, Long registrationId, Long societyId) {
		
		agent.setSociety(societyRepository.getOne(societyId));
		agentService.addAgent(agent);
		registrationService.addShopRegistrationAgent(agent, registrationId);
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/modificationInscriptionEvenementBoutique", model);
    }
	
	
	@PostMapping("/deleteShopRegistrationAgent")
    public ModelAndView deleteShopAgent(@RequestParam Long agentRegistrationId, Long registrationId) {
		
		Agent agent = agentRepository.getOne(agentRegistrationId);
		registrationService.deleteShopRegistrationAgent(agent, registrationId);
		agentService.deleteAgent(agent);
		ModelMap model = new ModelMap();
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/modificationInscriptionEvenementBoutique", model);
    }
}

