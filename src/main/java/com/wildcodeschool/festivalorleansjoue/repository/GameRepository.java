package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wildcodeschool.festivalorleansjoue.entity.Game;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

	public List<Game> findBySociety (Society society);
	
	public List<Game> findByEditorRegistrations (EditorRegistration editorRegistration);
	
	public List<Game> findBySocietyAndEditorRegistrationsNotOrEditorRegistrationsIsNull (Society society, EditorRegistration editorRegistration);
	
}
