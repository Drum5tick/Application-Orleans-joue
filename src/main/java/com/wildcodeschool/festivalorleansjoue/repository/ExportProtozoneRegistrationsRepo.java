package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ExportProtozoneRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportShopRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;

@Repository
public interface ExportProtozoneRegistrationsRepo extends JpaRepository<ExportProtozoneRegistrations, Long>{


	@Query(value="SELECT 		pr.id as id,   \n" + 
			"						       	pr.id as id_inscription,   \n" + 
			"						       	u.firstname as prenom,  \n" + 
			"			                  	u.lastname as nom,  \n" + 
			"						       	u.email as email,   \n" + 
			"						       	e.name as nom_evenement,   \n" + 
			"						       	e.event_beginning_date as debut_evenement,   \n" + 
			"						       	e.event_ending_date as fin_evenement,     \n" + 
			"						       	pr.subscription_date as date_demande_inscription,   \n" + 
			"						       	pr.state as etat_demande  \n" + 
			"						FROM `protozone_registration` pr inner join event e on e.id =pr.event_id   \n" + 
			"														inner join user u on u.id=pr.user_id   \n" + 
			"						                               left join address ad on u.address_id = ad.id  \n" + 
			"			                                         \n" + 
			"						WHERE event_id =:eventId"
			, nativeQuery = true)
			List<ExportProtozoneRegistrations> exportAllProtozoneRegistrationsSQL(@Param("eventId") Long eventId);
}