package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.ProtozoneRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.entity.User;

@Repository
public interface ProtozoneRegistrationRepository extends JpaRepository<ProtozoneRegistration, Long> {

	public List<ProtozoneRegistration> findByUser(User connectedUser);
	public List<ProtozoneRegistration> findByState(String state);
	public List<ProtozoneRegistration> findAllProtozoneRegistrationByEvent(Event event);
	public List<ProtozoneRegistration> findByEventAndState(Event event, String string);
	public List<ProtozoneRegistration> findByEvent(Event event);

	
	@Query(value="SELECT pr.* FROM protozone_registration pr inner join user u on pr.user_id = u.id WHERE pr.event_id =:eventId and u.firstname LIKE CONCAT('%',:userName,'%')", nativeQuery = true)
	List<ProtozoneRegistration> findByEventAndContainFirstname(@Param("eventId") Long eventId,@Param("userName") String userName);
	
	@Query(value="SELECT pr.* FROM protozone_registration pr inner join user u on pr.user_id = u.id WHERE pr.event_id =:eventId and u.lastname LIKE CONCAT('%',:userName,'%')", nativeQuery = true)
	List<ProtozoneRegistration> findByEventAndContainLastname(@Param("eventId") Long eventId,@Param("userName") String userName);

}
