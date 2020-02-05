package com.wildcodeschool.festivalorleansjoue.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.wildcodeschool.festivalorleansjoue.services.UserService;


public class CustomAuthenticationProvider implements AuthenticationManager {
	
	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
	    String password = (String) authentication.getCredentials();
	    List<GrantedAuthority> grantedAuths = new ArrayList<>();
	    grantedAuths.add(new SimpleGrantedAuthority(userService.findByEmail(email).getUserRole().getWording()));
	    return new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
	}

	

}
