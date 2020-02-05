package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ExportRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.ExportShopRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;

@Repository
public interface ExportShopRegistrationsRepo extends JpaRepository<ExportShopRegistrations, Long>{


	@Query(value="SELECT 	sr.id as id,  \n" + 
			"						       sr.id as id_inscription,  \n" + 
			"						       s.name as nom_societé,  \n" + 
			"						       ad.contact_email as mail_contact,  \n" + 
			"						       e.name as nom_evenement,  \n" + 
			"						       e.editors_registration_begin_date as debut_evenement,  \n" + 
			"						       e.editors_registration_end_date as fin_evenement,  \n" + 
			"						       e.price_per_table as prix_par_table,  \n" + 
			"						       e.tables as nombre_de_tables,  						       \n" + 
			"						       sr.tables_quantity as nb_tables_demandees, 			         \n" + 
			"						       sr.subscription_date as date_demande_inscription,   \n" + 
			"						       sr.agent_provided as representants,  \n" + 
			"						       sr.electrical_supply_need as besoin_electricite,  \n" + 
			"						       sr.registration_cost as cout_inscription,  \n" + 
			"						       sr.state as etat_demande, \n" + 
			"                               count(ag.id) as nombre_representants_renseignes\n" + 
			"						FROM `shop_registration` sr inner join event e on e.id =sr.event_id  \n" + 
			"														inner join society s on s.id=sr.society_id  \n" + 
			"						                               inner join address ad on s.address_id = ad.id \n" + 
			"                                                       left join agent_shop_registration asr on sr.id = asr.shop_registration_id\n" + 
			"                                                       left join agent ag on asr.agent_id = ag.id\n" + 
			"						                                \n" + 
			"                               						WHERE event_id =1 \n" + 
			"                        group by sr.id,  \n" + 
			"						       s.name,  \n" + 
			"						       ad.contact_email,  \n" + 
			"						       e.name,  \n" + 
			"						       e.editors_registration_begin_date,  \n" + 
			"						       e.editors_registration_end_date,  \n" + 
			"						       e.price_per_table,  \n" + 
			"						       e.tables,  						       \n" + 
			"						       sr.tables_quantity, 			         \n" + 
			"						       sr.subscription_date,   \n" + 
			"						       sr.agent_provided,  \n" + 
			"						       sr.electrical_supply_need,  \n" + 
			"						       sr.registration_cost,  \n" + 
			"						       sr.state\n"
			, nativeQuery = true)
			List<ExportShopRegistrations> exportAllShopRegistrationsSQL(@Param("eventId") Long eventId);
}