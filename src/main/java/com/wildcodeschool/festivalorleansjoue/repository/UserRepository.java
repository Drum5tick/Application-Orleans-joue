package com.wildcodeschool.festivalorleansjoue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.entity.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	public List<User> findByUserRole(UserRole role);
	public List<User> findByFirstnameContaining(String firstname);
	public List<User> findByLastnameContaining(String lastname);
	public List<User> findBySociety(Society society);
	public User findByFirstname(String firstname);
	
}
