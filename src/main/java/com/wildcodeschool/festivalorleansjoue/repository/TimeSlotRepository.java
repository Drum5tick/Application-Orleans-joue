package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.TimeSlot;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long>{

	public ArrayList<TimeSlot> findAllByOrderByOrdernumberAsc();
}
