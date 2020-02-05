package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Society;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long>{

	public Society findByName(String name);
	public List<Society> findByNameContaining(String name);

}
