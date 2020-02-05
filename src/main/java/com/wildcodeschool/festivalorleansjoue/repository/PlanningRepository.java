package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Planning;
import com.wildcodeschool.festivalorleansjoue.entity.VolunteerRegistration;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long>{

	public List<Planning> findPlanningByVolunteerRegistration (VolunteerRegistration volunteerRegistration);
}
