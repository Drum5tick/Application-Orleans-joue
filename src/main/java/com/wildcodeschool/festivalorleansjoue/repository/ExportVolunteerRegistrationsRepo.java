package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ExportRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportVolunteerRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;

@Repository
public interface ExportVolunteerRegistrationsRepo extends JpaRepository<ExportVolunteerRegistrations, Long>{


	@Query(value="SELECT 		vr.id as id, \n" + 
			"			       	vr.id as id_inscription, \n" + 
			"			       	u.firstname as prenom,\n" + 
			"                  	u.lastname as nom,\n" + 
			"			       	u.email as email, \n" + 
			"			       	e.name as nom_evenement, \n" + 
			"			       	e.editors_registration_begin_date as debut_evenement, \n" + 
			"			       	e.editors_registration_end_date as fin_evenement, \n" + 
			"			       	e.price_per_table as prix_par_table, \n" + 
			"			       	e.tables as nombre_de_tables,  \n" + 
			"					0 as besoin_volontaires_total_evenement,\n" + 
			"			       	vr.subscription_date as date_demande_inscription, \n" + 
			"			       	vr.state as etat_demande\n" + 
			"			FROM `volunteer_registration` vr inner join event e on e.id =vr.event_id \n" + 
			"											inner join user u on u.id=vr.user_id \n" + 
			"			                               left join address ad on u.address_id = ad.id\n" + 
			"                                       \n" + 
			"			WHERE event_id =:eventId \n"
			, nativeQuery = true)
			List<ExportVolunteerRegistrations> exportVolunteerAllRegistrationsSQL(@Param("eventId") Long eventId);
}