package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wildcodeschool.festivalorleansjoue.entity.Agent;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{

	public List<Agent> findBySociety (Society society);
	
	public List<Agent> findByEditorRegistrations (EditorRegistration editorRegistration);
	
	public List<Agent> findByShopRegistrations(ShopRegistration shopRegistrations);

	public List<Agent> findBySocietyAndEditorRegistrationsNotOrEditorRegistrationsIsNull (Society society, EditorRegistration editorRegistration);
}
