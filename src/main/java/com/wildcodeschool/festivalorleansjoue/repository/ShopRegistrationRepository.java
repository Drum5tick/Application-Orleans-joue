package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Event;
import com.wildcodeschool.festivalorleansjoue.entity.Invoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopInvoice;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;

@Repository
public interface ShopRegistrationRepository extends JpaRepository<ShopRegistration, Long>{

	
	public List<ShopRegistration> findByState(String state);
	public List<ShopRegistration> findBySociety(Society society); 
	public List<ShopRegistration> findByEvent(Event event); 
	public List<ShopRegistration> findByEventAndState(Event event,String state); 
	public ShopRegistration findByShopInvoice(ShopInvoice shopInvoice);

	@Query(value="SELECT sr.* FROM shop_registration sr inner join society s on sr.society_id = s.id WHERE sr.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%')", nativeQuery = true)
	List<ShopRegistration> findByEventAndContainSociety(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
	@Query(value="SELECT sr.* FROM shop_registration sr inner join society s on sr.society_id = s.id WHERE sr.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%') ORDER BY sr.tables_quantity DESC", nativeQuery = true)
	List<ShopRegistration> findByEventAndContainSocietyOrderByTableCount(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
	@Query(value="SELECT sr.* FROM shop_registration sr inner join society s on sr.society_id = s.id WHERE sr.event_id =:eventId and s.name LIKE CONCAT('%',:societyName,'%') ORDER BY s.name", nativeQuery = true)
	List<ShopRegistration> findByEventAndContainSocietyOrderByName(@Param("eventId") Long eventId,@Param("societyName") String societyName);
	
}
