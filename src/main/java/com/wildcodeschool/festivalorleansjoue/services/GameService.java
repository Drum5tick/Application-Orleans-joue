package com.wildcodeschool.festivalorleansjoue.services;

import java.io.File;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.wildcodeschool.festivalorleansjoue.entity.Game;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private String baseDir;

	@Value("${app.uploadPictures.dir}")
	private String uploadPicturesDir;
	
	public List<Game> ReturnGamesBySociety (Society society){
		
		return gameRepository.findBySociety(society);
	}
	
	
	public List<Game> findByRegistration (EditorRegistration editorRegistration){
		
		return gameRepository.findByEditorRegistrations(editorRegistration);
	}
	
	public List<Game> findByRegistrationNotSociety (Society society,EditorRegistration editorRegistration){
		
		return gameRepository.findBySocietyAndEditorRegistrationsNotOrEditorRegistrationsIsNull(society, editorRegistration);
	}
	
	public void addGame (Game game) {
				
		gameRepository.save(game);
	}
	

	public void deleteGame (Game game) {
		
		gameRepository.delete(game);
	}

	
	public void modifyGame (Game game, MultipartFile filePicture) {
		
		Optional<Game> originalGame = gameRepository.findById(game.getId());
		if(game.getName()!="")
			originalGame.get().setName(game.getName());
		if(game.getAuthor()!="")
			originalGame.get().setAuthor(game.getAuthor());
		if(game.getEditor()!="")
			originalGame.get().setEditor(game.getEditor());
		if(game.getDistributor()!="")
			originalGame.get().setDistributor(game.getDistributor());
		if(game.getDescription()!="")
			originalGame.get().setDescription(game.getDescription());
		if(game.getName()!="")
			originalGame.get().setName(game.getName());
		if(game.getCategory()!="")
			originalGame.get().setCategory(game.getCategory());
		if(!filePicture.isEmpty()) {
			originalGame.get().setPicture(uploadPicturesDir + File.separator + fileService.uploadFile(filePicture));
		}
		if(game.getPublicationDate()!=null)
			originalGame.get().setPublicationDate(game.getPublicationDate());
		if(game.getPrice()!=0)
			originalGame.get().setPrice(game.getPrice());
		game.setSociety(originalGame.get().getSociety());
		gameRepository.save(originalGame.get());
	}
}
