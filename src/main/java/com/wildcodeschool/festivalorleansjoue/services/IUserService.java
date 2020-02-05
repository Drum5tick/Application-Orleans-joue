package com.wildcodeschool.festivalorleansjoue.services;

import com.wildcodeschool.festivalorleansjoue.entity.User;


public interface IUserService {

	public User returnConnectedUser();
	public void registrateUser(User user, String userRole);
	public User findByEmail(String email);	
	void saveUser(User user);
}
