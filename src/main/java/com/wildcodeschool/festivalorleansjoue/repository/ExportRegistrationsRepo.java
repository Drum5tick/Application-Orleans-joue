package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ExportRegistrations;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;

@Repository
public interface ExportRegistrationsRepo extends JpaRepository<ExportRegistrations, Long>{


	@Query(value="SELECT 	er.id as id,\n" + 
			"        er.id as id_inscription,\n" + 
			"        s.name as nom_societé,\n" + 
			"        ad.contact_email as mail_contact,\n" + 
			"        e.name as nom_evenement,\n" + 
			"        e.editors_registration_begin_date as debut_evenement,\n" + 
			"        e.editors_registration_end_date as fin_evenement,\n" + 
			"        e.price_per_table as prix_par_table,\n" + 
			"        e.tables as nombre_de_tables,\n" + 
			"        er.id as id_inscription,\n" + 
			"        er.tables_quantity as nb_tables_demandees,\n" + 
			"        count(ga.game_id) as nb_jeux_inscrits,\n" + 
			"        er.sale_option as option_vente,\n" + 
			"        er.subscription_date as date_demande_inscription,\n" + 
			"        er.volunteers_need as besoin_volontaire,\n" + 
			"        er.agent_provided as representants,\n" + 
			"        er.electrical_supply_need as besoin_electricite,\n" + 
			"        er.registration_cost as cout_inscription,\n" + 
			"        er.state as etat_demande,\n" + 
			"        er.invoice_id as numero_facture\n" + 
			"FROM `editor_registration` er inner join event e on e.id =er.event_id\n" + 
			"								inner join society s on s.id=er.society_id\n" + 
			"                                inner join address ad on s.address_id = ad.id\n" + 
			"                                left join game_registration ga on ga.editor_registration_id = er.id\n" + 
			"WHERE event_id =:eventId\n" + 
			"GROUP BY er.id,\n" + 
			"        s.name,\n" + 
			"        ad.contact_email,\n" + 
			"        e.name,\n" + 
			"        e.editors_registration_begin_date,\n" + 
			"        e.editors_registration_end_date,  \n" + 
			"        e.price_per_table,\n" + 
			"        e.tables,\n" + 
			"        er.id,\n" + 
			"        er.tables_quantity,\n" + 
			"        er.sale_option,\n" + 
			"        er.subscription_date,\n" + 
			"        er.volunteers_need,\n" + 
			"        er.agent_provided,\n" + 
			"        er.electrical_supply_need,\n" + 
			"        er.registration_cost,\n" + 
			"        er.state,\n" + 
			"        er.invoice_id", nativeQuery = true)
			List<ExportRegistrations> exportAllRegistrationsSQL(@Param("eventId") Long eventId);
}