package com.wildcodeschool.festivalorleansjoue.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wildcodeschool.festivalorleansjoue.entity.Agent;
import com.wildcodeschool.festivalorleansjoue.entity.EditorRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.ShopRegistration;
import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.repository.AgentRepository;

@Service
public class AgentService {

	@Autowired
	AgentRepository agentRepository;
	
	
	public List<Agent> findAgentBySociety (Society society){
		
		return agentRepository.findBySociety(society);
	}
	
	
	public List<Agent> findAgentByRegistration (EditorRegistration editorRegistration){
		
		return agentRepository.findByEditorRegistrations(editorRegistration);
	}
	
	
	public List<Agent> findAgentByShopRegistrations(ShopRegistration shopRegistrations){
	
		return agentRepository.findByShopRegistrations(shopRegistrations);
	}
	

	public void addAgent (Agent agent) {
		
		agentRepository.save(agent);
	}
	

	public void deleteAgent (Agent agent) {
		
		agentRepository.delete(agent);
	}


	public Agent getAgent(Long agentId) {
		
		return agentRepository.getOne(agentId);
	}


	public void updateAgent(Agent agent) {
		
		Agent oldAgent = agentRepository.getOne(agent.getId());
		oldAgent.setFirstname(agent.getFirstname());
		oldAgent.setLastname(agent.getLastname());
		oldAgent.setEmail(agent.getEmail());
		oldAgent.setPhone(agent.getPhone());
		agentRepository.save(oldAgent);
	}
		
}
