package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ExportRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportVolunteerPlanning;
import com.wildcodeschool.festivalorleansjoue.entity.ExportVolunteerRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;

@Repository
public interface ExportVolunteerPlanningRepo extends JpaRepository<ExportVolunteerPlanning, Long>{


	@Query(value="SELECT 		vr.id as id,  \n" + 
			"						       	vr.id as id_inscription,  \n" + 
			"						       	u.firstname as prenom, \n" + 
			"			                  	u.lastname as nom, \n" + 
			"						       	u.email as email,  \n" + 
			"						       	e.name as nom_evenement,  \n" + 
			"                                p.task_name as tache,\n" + 
			"                                p.time_slot_name as jour,\n" + 
			"                                p.time_slot_description as tranche_horaire\n" + 
			"                               \n" + 
			"                                \n" + 
			"						FROM `volunteer_registration` vr inner join event e on e.id =vr.event_id  \n" + 
			"														inner join user u on u.id=vr.user_id  \n" + 
			"                                                       left join volunteer_registration_planning vrp on vr.id = vrp.volunteer_registration_id\n" + 
			"                                                       left join planning p on vrp.planning_id = p.id\n" + 
			"			                                        \n" + 
			"						WHERE event_id =:eventId "
			, nativeQuery = true)
			List<ExportVolunteerPlanning> exportVolunteerPlanningSQL(@Param("eventId") Long eventId);
}