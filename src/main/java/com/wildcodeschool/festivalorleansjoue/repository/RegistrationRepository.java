package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.Invoice;
import com.wildcodeschool.festivalorleansjoue.entity.Society;

@Repository
public interface RegistrationRepository extends JpaRepository<EditorRegistration, Long> {

	public List<EditorRegistration> findByState(String state);
	public List<EditorRegistration> findBySociety(Society society); 
	public List<EditorRegistration> findByEvent(Event event); 
	public List<EditorRegistration> findByEventAndState(Event event,String state); 
	public EditorRegistration findByInvoice(Invoice invoice); 
	
	@Query(value="SELECT er.* FROM editor_registration er inner join society s on er.society_id = s.id WHERE er.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%')", nativeQuery = true)
	List<EditorRegistration> findByEventAndContainSociety(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
	@Query(value="SELECT er.* FROM editor_registration er inner join society s on er.society_id = s.id WHERE er.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%') ORDER BY s.name", nativeQuery = true)
	List<EditorRegistration> findByEventAndContainSocietyOrderBySocietyName(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
	@Query(value="SELECT er.* FROM editor_registration er inner join society s on er.society_id = s.id WHERE er.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%') ORDER BY er.tables_quantity DESC", nativeQuery = true)
	List<EditorRegistration> findByEventAndContainSocietyOrderByTableCount(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
	@Query(value="SELECT er.* FROM editor_registration er inner join society s on er.society_id = s.id WHERE er.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%') ORDER BY er.volunteer_count DESC", nativeQuery = true)
	List<EditorRegistration> findByEventAndContainSocietyOrderByVolunteerCount(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
	@Query(value="SELECT er.* FROM editor_registration er inner join society s on er.society_id = s.id WHERE er.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%') ORDER BY er.registration_cost DESC", nativeQuery = true)
	List<EditorRegistration> findByEventAndContainSocietyOrderByCost(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
}
