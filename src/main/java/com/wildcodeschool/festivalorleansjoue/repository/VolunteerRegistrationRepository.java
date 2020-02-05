package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;

@Repository
public interface VolunteerRegistrationRepository extends JpaRepository<VolunteerRegistration, Long> {

	public List<VolunteerRegistration> findByUser(User connectedUser);
	public List<VolunteerRegistration> findByState(String state);
	public List<VolunteerRegistration> findAllVolunteerRegistrationByEvent(Event event);
	public List<VolunteerRegistration> findByEventAndState(Event event,String state);
	
	
}
