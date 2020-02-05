package com.wildcodeschool.festivalorleansjoue.controllers;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Tasks;
import com.wildcodeschool.festivalorleansjoue.entity.TimeSlot;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.VolunteerRegistrationRepository;
import com.wildcodeschool.festivalorleansjoue.services.AdminService;
import com.wildcodeschool.festivalorleansjoue.services.AgentService;
import com.wildcodeschool.festivalorleansjoue.services.FileService;
import com.wildcodeschool.festivalorleansjoue.services.ProtozoneRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.ShopRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.VolunteerRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.EventService;
import com.wildcodeschool.festivalorleansjoue.services.EditorRegistrationService;
import com.wildcodeschool.festivalorleansjoue.services.EventService;
import com.wildcodeschool.festivalorleansjoue.services.FileService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private FileService fileService;

	@Autowired
	private EditorRegistrationService registrationService;

	@Autowired
	ShopRegistrationService shopRegistrationService;

	@Autowired
	VolunteerRegistrationService volunteerRegistrationService;

	@Autowired
	VolunteerRegistrationRepository volunteerRegistrationRepo;

	@Autowired
	private EventService eventService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private ProtozoneRegistrationService protozoneRegistrationService;

	@Autowired
	private String baseDir;

	@Value("${app.uploadPictures.dir}")
	private String uploadPicturesDir;

//Dashbord Section

	@RequestMapping("admin/accueilAdmin")
	public ModelAndView admin() {

		return adminService.buildAdminHomeView();
	}

//Event Section

	@RequestMapping("admin/gestionEvenements")
	public ModelAndView adminEvent() {
		return adminService.buildAdminEventView();
	}

	@PostMapping("/admin/addEvent")
	public ModelAndView postNewEvent(@ModelAttribute Event event) {
		adminService.createEvent(event);
		return new ModelAndView("redirect:/admin/gestionEvenements", new ModelMap());

	}	
	
	@PostMapping("admin/selectEvent")
	public ModelAndView selectEventById(@RequestParam Long eventId) {

		adminService.selectEventById(eventId);
		return adminService.buildAdminEventView();
	}

	@PostMapping("admin/updateEvent")
	public ModelAndView updateEvent(@ModelAttribute Event event) {
		adminService.updateEvent(event);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseUpdateEvent", new ModelMap());
	}

	@PostMapping("admin/deleteEvent")
	public ModelAndView deleteEvent(@RequestParam Long eventId) {
		Event event = eventService.findById(eventId);
		int count = 0;

		count = count + registrationService.findByEvent(event).size();
		count = count + shopRegistrationService.findByEvent(event).size();
		count = count + volunteerRegistrationService.findAllVoluteerByEvent(event).size();
		count = count + protozoneRegistrationService.findAllProtozoneByEvent(event).size();
		if (count == 0) {
			adminService.deleteEventById(eventId);
		}

		return adminService.buildAdminEventView();
	}

	@PostMapping("admin/addTaskToEvent")
	public ModelAndView addTaskToEvent(@RequestParam Long eventId, @RequestParam Long taskId) {
		adminService.addTaskToEvent(eventId, taskId);
		adminService.selectEventById(eventId);
		return adminService.buildAdminEventView();
	}

	@PostMapping("admin/deleteTaskFromEvent")
	public ModelAndView deleteTaskFromEvent(@RequestParam Long eventId, @RequestParam Long eventTaskId) {
		adminService.deleteTaskFromEvent(eventTaskId);
		adminService.selectEventById(eventId);
		return adminService.buildAdminEventView();

	}

	@PostMapping("admin/createTask")
	public ModelAndView createOneTask(@ModelAttribute Tasks task) {
		adminService.createTask(task);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTask", new ModelMap());
	}

	@PostMapping("admin/selectTask")
	public ModelAndView selectTask(@RequestParam Long taskId) {
		adminService.selectTaskById(taskId);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTask", new ModelMap());
	}

	@PostMapping("admin/updateTask")
	public ModelAndView updateTask(@ModelAttribute Tasks task) {
		adminService.updateTask(task);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTask", new ModelMap());
	}

	@PostMapping("admin/deleteTask")
	public ModelAndView deleteTask(@RequestParam Long taskId) {
		adminService.deleteTaskById(taskId);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTask", new ModelMap());
	}

	//Event timeslots
	@PostMapping("admin/createTimeslot")
	public ModelAndView createOneTimesLot(@ModelAttribute TimeSlot timeslot) {
		adminService.createTimeslot(timeslot);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTimeslot", new ModelMap());
	}

	@PostMapping("admin/selectTimeslot")
	public ModelAndView selectTimeslot(@RequestParam Long timeslotId) {
		adminService.selectTimeSlotById(timeslotId);
		return new ModelAndView("redirect:/admin/gestionEvenements#modifyTimeslot", new ModelMap());
	}

	@PostMapping("admin/updateTimeslot")
	public ModelAndView updateTimeslot(@ModelAttribute TimeSlot timeslot) {
		adminService.updateTimeslot(timeslot);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTimeslot", new ModelMap());
	}

	@PostMapping("admin/deleteTimeslot")
	public ModelAndView deleteTimeslot(@RequestParam Long timeslotId) {
		adminService.deleteTimeSlotById(timeslotId);
		return new ModelAndView("redirect:/admin/gestionEvenements#collapseTimeslot", new ModelMap());
	}
  
//General Registration

	@RequestMapping("admin/gestionDesInscriptions/{name}" )
	public ModelAndView adminRegistrations(@PathVariable String name, @RequestParam (name="eventId", required=false) Long eventId){
		if(name.equals("boutiques"))
			return adminService.buildAdminRegistrationsShopView(eventId);
		else if (name.equals("protozone"))
			return adminService.buildAdminRegistrationsProtozoneView(eventId);
		return new ModelAndView("redirect:/error");
	}

//Editor Registration Section

	@RequestMapping("admin/gestionDesInscriptions")
	public ModelAndView adminRegistrations(@RequestParam(name = "eventId", required = false) Long eventId) {
		return adminService.buildAdminRegistrationsView(eventId);
	}

	@RequestMapping(value = "admin/submitAdminForRegistration", method = RequestMethod.POST)
	public ModelAndView submitAdminForRegistration(@RequestParam(value = "eventId", required = false) Long eventId) {
		adminService.setToggleforSelectedRegistration();
		ModelMap model = new ModelMap();
		model.addAttribute("eventId", eventId);
		return new ModelAndView("redirect:/admin/gestionDesInscriptions", model);
	}

	@RequestMapping(value = "admin/researchForRegistrations", method = RequestMethod.POST)
	public ModelAndView researchForRegistration(
			@RequestParam(value = "societyName", required = true) String societyName,
			@RequestParam(value = "eventId", required = false) Long eventId,
			@RequestParam(required = false) String orderBy) {

		adminService.setRegistrationsResults(eventId, societyName, orderBy);
		return adminService.buildAdminRegistrationsSearch(eventId, societyName);
	}

	@PostMapping("admin/validateRegistration")
	public ModelAndView validateRegistration(@RequestParam Long eventId, @RequestParam Long editorRegId) {
		adminService.validateRegistration(editorRegId);
		return adminService.buildAdminRegistrationsView(eventId);
	}

	@PostMapping("admin/unvalidateRegistration")
	public ModelAndView unvalidateRegistration(@RequestParam Long eventId, @RequestParam Long editorRegId) {
		adminService.unvalidateRegistration(editorRegId);
		return adminService.buildAdminRegistrationsView(eventId);
	}

	@PostMapping("admin/cancelRegistration")
	public ModelAndView cancelRegistration(@RequestParam Long eventId, @RequestParam Long editorRegId) {
		adminService.cancelRegistration(editorRegId);
		return adminService.buildAdminRegistrationsView(eventId);
	}

	@PostMapping("admin/reactivateRegistration")
	public ModelAndView reactivateRegistration(@RequestParam Long eventId, @RequestParam Long editorRegId) {
		adminService.reactivateRegistration(editorRegId);
		return adminService.buildAdminRegistrationsView(eventId);
	}

	@PostMapping("admin/deleteRegistration")
	public ModelAndView deleteRegistration(@RequestParam Long eventId, @RequestParam Long editorRegId) {
		adminService.deleteRegistration(editorRegId);
		return adminService.buildAdminRegistrationsView(eventId);
	}
	
	@RequestMapping(value = "admin/editerInscription", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView showRegistrationEditPage(@RequestParam Long registrationId, Optional<String> toastMsg) {
		return adminService.buildEditorRegistrationModif(registrationId, toastMsg);
	}
	
	@PostMapping("admin/saveModifiedEditorReg")
	public ModelAndView saveEditorRegistrationModif(@ModelAttribute EditorRegistration registration, Long registrationId) {
		registration.setId(registrationId);
		registrationService.updateEditorRegistrationService(registration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "accountModifSaved");
		model.addAttribute("registrationId", registrationId);
		return new ModelAndView("redirect:/admin/editerInscription", model);
	}
	
	@GetMapping("admin/editerCollaborateurEditeur")
	public ModelAndView agentEdition(@RequestParam Long agentRegistrationId, Long registrationId,
			Optional<String> toastMsg) {
		adminService.updateAgent(agentRegistrationId, registrationId);
		ModelMap model = adminService.buildEditorRegistrationModif(registrationId, null).getModelMap();
		return new ModelAndView("redirect:/admin/editerInscription#selectedAgent", model);
	}


//Volunteer Registration

	@GetMapping("admin/gestionBenevoles")
	public ModelAndView adminVolunteer(@RequestParam(name = "eventId", required = false) Long eventId,
			String toggleCard) {

		return adminService.buildAdminVolunteerView(eventId, toggleCard);
	}

	@RequestMapping(value = "admin/submitAdminForVolunteer", method = RequestMethod.POST)
	public ModelAndView submitAdminForVolunteer(@RequestParam(value = "eventId", required = false) Long eventId) {

		adminService.setToggleforSelectedRegistration();
		ModelMap model = new ModelMap();
		model.addAttribute("eventId", eventId);
		return new ModelAndView("redirect:/admin/gestionBenevoles", model);
	}

	@PostMapping("admin/submitVolunteerschedule")
	public ModelAndView submitVolunteerschedule(@RequestParam Long volunteerRegistrationId, @RequestParam Long taskId,
			@RequestParam Long timeSlotId, @RequestParam Long eventId) {

		adminService.submitVolunteerSchedule(volunteerRegistrationId, taskId, timeSlotId);
		ModelMap model = new ModelMap();
		model.addAttribute("tasks", taskId);
		model.addAttribute("eventId", eventId);
		model.addAttribute("timeSlot", timeSlotId);
		model.addAttribute("toggleCard", volunteerRegistrationId);

		return new ModelAndView("redirect:/admin/gestionBenevoles#collapse" + volunteerRegistrationId, model);
	}

	@PostMapping("admin/deleteVolunteerschedule")
	public ModelAndView deletVolunteerschedule(@RequestParam Long planningId, @RequestParam Long registrationId,
			@RequestParam Long eventId) {
		adminService.updateSchedule(registrationId, planningId);
		ModelMap model = new ModelMap();
		model.addAttribute("eventId", eventId);
		model.addAttribute("toggleCard", registrationId);
		return new ModelAndView("redirect:/admin/gestionBenevoles#collapse" + registrationId, model);
	}

	@PostMapping("admin/deleteVolunteerRegistration")
	public ModelAndView deleteVolunteerRegistration(@RequestParam Long eventId, @RequestParam Long volunteerRegId) {
		adminService.deleteVolunteerRegistration(volunteerRegId);
		ModelMap model = new ModelMap();
		model.addAttribute("eventId", eventId);
		return new ModelAndView("redirect:/admin/gestionBenevoles", model);
	}

	@RequestMapping(value = "/admin/editerInscriptionBenevole", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView modifyVolunteerRegistration(@RequestParam Long registrationId, Optional<String> toastMsg) {

		adminService.getMav().clear();
		return adminService.buildVolunteerRegistrationModif(registrationId, toastMsg);
	}

	@PostMapping("/admin/saveModifiedVolunteerReg")
	public ModelAndView saveModifiedVolunteerModif(@ModelAttribute VolunteerRegistration registration,
			Long registrationId) {
		VolunteerRegistration originalRegistration = volunteerRegistrationService.findVolunteerById(registrationId);
		originalRegistration.setChosenTimeSlot(registration.getChosenTimeSlot());
		originalRegistration.setChosenTasks(registration.getChosenTasks());
		volunteerRegistrationRepo.save(originalRegistration);
		ModelMap model = new ModelMap();
		model.addAttribute("toastMsg", "accountModifSaved");
		model.addAttribute("eventId", originalRegistration.getEvent().getId());
		return new ModelAndView("redirect:/admin/gestionBenevoles", model);
	}

//Account Section

	@GetMapping("admin/gestionDesComptes")
	public ModelAndView adminAccount() {
		return adminService.buildAdminAccountView();
	}

	@PostMapping("admin/createAccount")
	public ModelAndView createAccount(@ModelAttribute User user) {
		adminService.createAccount(user);
		return adminService.buildAdminAccountView();
	}

	@PostMapping("admin/selectAccount")
	public ModelAndView selectAccountById(@RequestParam Long accountId) {
		adminService.selectAccountById(accountId);
		return adminService.buildAdminAccountView();
	}

	@PostMapping("admin/updateAccount")
	public ModelAndView updateAccount(@ModelAttribute(name = "selectedAccount") User user,
			@RequestParam(required = false) MultipartFile filePicture, @RequestParam String toggleCard) {
		if (filePicture != null && !filePicture.isEmpty())
			user.setProfilePicture(uploadPicturesDir + File.separator + fileService.uploadFile(filePicture));
		adminService.updateAccount(user, toggleCard);
		return adminService.buildAdminAccountView();
	}

	@PostMapping("admin/deleteAccount")
	public ModelAndView deleteAccount(@RequestParam Long accountId, @RequestParam(required = false) String savedSearch,
			@RequestParam String toggleCard) {
		adminService.deleteAccount(accountId, toggleCard);
		if (savedSearch != null)
			adminService.searchAccountByUserName(savedSearch);
		return adminService.buildAdminAccountView();
	}

	@PostMapping("admin/searchAccountByUserName")
	public ModelAndView searchAccountByUserName(@RequestParam String userName) {
		adminService.searchAccountByUserName(userName);
		return adminService.buildAdminAccountView();
	}

	@GetMapping("admin/closeAllAccountSection")
	public ModelAndView closeAllAccountSection() {
		adminService.getMav().clear();
		return new ModelAndView("redirect:/admin/gestionDesComptes", new ModelMap());
	}

}
