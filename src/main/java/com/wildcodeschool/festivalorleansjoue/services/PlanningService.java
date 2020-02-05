package com.wildcodeschool.festivalorleansjoue.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.Planning;
import com.wildcodeschool.festivalorleansjoue.entity.PlanningObject;
import com.wildcodeschool.festivalorleansjoue.entity.Tasks;
import com.wildcodeschool.festivalorleansjoue.entity.TimeSlot;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;
import com.wildcodeschool.festivalorleansjoue.repository.EventRepository;
import com.wildcodeschool.festivalorleansjoue.repository.PlanningRepository;
import com.wildcodeschool.festivalorleansjoue.repository.TasksRepository;
import com.wildcodeschool.festivalorleansjoue.repository.TimeSlotRepository;
import com.wildcodeschool.festivalorleansjoue.repository.VolunteerRegistrationRepository;

@Service
public class PlanningService {

	@Autowired
	PlanningRepository planningRepository;

	@Autowired
	TasksRepository tasksRepository;
	@Autowired
	TimeSlotRepository timeSlotRepository;
	@Autowired
	VolunteerRegistrationRepository volunteerRegistrationRepository;
	@Autowired
	VolunteerRegistrationService volunteerRegistrationService;
	@Autowired
	EventRepository eventRepository;
		
	public List<Planning> findPlanningByVolunteerRegistration (VolunteerRegistration volunteerRegistration){
		
		return planningRepository.findPlanningByVolunteerRegistration(volunteerRegistration);
	}
	
	
	public void addPlanning (Planning planning) {
		
		planningRepository.save(planning);
	}
	

	public void deletePlanning (Planning planning) {
		
		planningRepository.delete(planning);
	}

	public List<PlanningObject> planningCalcul(Event event){
		List<PlanningObject> planningResult = new ArrayList<PlanningObject>();
		List<Tasks> tasks = tasksRepository.findAll();
		List<TimeSlot> timeSlots = timeSlotRepository.findAll();
		List<VolunteerRegistration> volunteerRegistrations = volunteerRegistrationService.findAllVoluteerByEvent(event);		
		PlanningObject planningObject = new PlanningObject();
		
		for (TimeSlot timeSlot : timeSlots) {
			for(Tasks task : tasks) {
				planningObject = new PlanningObject();
				planningObject.setTimeSlotName(timeSlot.getName());
				planningObject.setTimeSlotDescription(timeSlot.getDescription());
				planningObject.setTaskName(task.getName());
				planningObject.setVolunteerCount(0l);
				for(VolunteerRegistration registration : volunteerRegistrations) {
					for (Planning planning : registration.getPlanning()) {
						if (planning.getTaskName().equals(task.getName())) {
							if (planning.getTimeSlotDescription().equals(timeSlot.getDescription()) && planning.getTimeSlotName().equals(timeSlot.getName())) {
								planningObject.setVolunteerCount(planningObject.getVolunteerCount()+1);
							}
						}
					}
				}
				planningResult.add(planningObject);
			}
			
		}
		return planningResult;
	}
}
