package com.wildcodeschool.festivalorleansjoue.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private String profilePicture;
	private boolean accountActive;
	private boolean completedUserInfos;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private UserRole userRole;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "society_id")
	private Society society;
	
	
	public User() {

	}
	

	public Long getId() {

		return this.id;
	}
	

	public void setId(Long id) {

		this.id = id;
	}
	

	public String getEmail() {

		return this.email;
	}
	

	public void setEmail(String email) {

		this.email = email;
	}
	

	public String getPassword() {

		return this.password;
	}
	

	public void setPassword(String password) {

		this.password = password;
	}
	

	public String getFirstname() {

		return this.firstname;
	}
	

	public void setFirstname(String firstname) {

		this.firstname = firstname;
	}
	

	public String getLastname() {

		return this.lastname;
	}
	

	public void setLastname(String lastname) {

		this.lastname = lastname;
	}


	public String getProfilePicture() {
		
		return profilePicture;
	}
	

	public void setProfilePicture(String profilePicture) {
		
		this.profilePicture = profilePicture;
	}
		
	
	public boolean isAccountActive() {
		
		return this.accountActive;
	}


	public void setAccountActive(boolean accountActive) {
		
		this.accountActive = accountActive;
	}
	
	
	public boolean isCompletedUserInfos() {
		
		return this.completedUserInfos;
	}


	public void setCompletedUserInfos(boolean completedUserInfos) {
		
		this.completedUserInfos = completedUserInfos;
	}


	public Address getAddress() {

		return this.address;
	}
	

	public void setAddress(Address address) {

		this.address = address;
	}
	

	public UserRole getUserRole() {

		return this.userRole;
	}
	

	public void setUserRole(UserRole userRole) {

		this.userRole = userRole;
	}


	public Society getSociety() {
		
		return this.society;
	}


	public void setSociety(Society society) {
		
		this.society = society;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.userRole.getWording()));
        return authorities;
	}


	@Override
	public String getUsername() {
		
		return this.email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
