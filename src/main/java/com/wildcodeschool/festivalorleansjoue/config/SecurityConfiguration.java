package com.wildcodeschool.festivalorleansjoue.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	private final String USERS_QUERY = "SELECT email, password, account_active FROM user WHERE email=?";
	private final String ROLES_QUERY = "SELECT u.email, ur.wording FROM user u JOIN user_role ur on (u.user_role_id = ur.id) WHERE u.email=?";
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery(USERS_QUERY)
			.authoritiesByUsernameQuery(ROLES_QUERY)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/login", "/typeUtilisateur", "/inscriptionEditeur", "/inscriptionBoutique", "/inscriptionBenevole", "/inscriptionProtozone", "/ajoutUtilisateur", "/userRegPage", "/loginError","error", "/CSS/**", "/JS/**", "/pictures/**").permitAll()
			.antMatchers("/accueil").hasAnyAuthority("ADMIN", "EDITOR", "SHOP", "VOLUNTEER", "PROTOZONE")
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login")
			.failureUrl("/loginError")
			.defaultSuccessUrl("/").usernameParameter("email").passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/uploads/**");
	}
}
