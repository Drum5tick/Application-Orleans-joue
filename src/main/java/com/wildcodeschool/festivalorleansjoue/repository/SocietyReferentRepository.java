package com.wildcodeschool.festivalorleansjoue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wildcodeschool.festivalorleansjoue.entity.SocietyReferent;
import com.wildcodeschool.festivalorleansjoue.entity.User;

public interface SocietyReferentRepository extends JpaRepository<SocietyReferent, Long> {
	
	public SocietyReferent findByUser(User user);
}
