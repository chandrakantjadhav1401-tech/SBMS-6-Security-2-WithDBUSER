package com.chanduandjava.security;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chanduandjava.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity

public class Security {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	
	@Bean
	public BCryptPasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationManager()
	{
		DaoAuthenticationProvider autherProvider=new DaoAuthenticationProvider(customUserDetailsService);
		autherProvider.setPasswordEncoder(encoder());
		
		return autherProvider;
	}
	
	
	
	@Bean
	public SecurityFilterChain manageSecurity(HttpSecurity http) throws Exception
	{
//		except the two API other api will need to login
		http.csrf(csrf->csrf.disable());  //enable asel ter request not send 
		
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers("/admin").hasRole("ADMIN");
			auth.requestMatchers("/user").hasRole("USER");
			auth.requestMatchers("/contact").hasAnyRole("ADMIN","USER");
 			auth.requestMatchers("/createuser","/welcome").
 			permitAll().
 			anyRequest().
 			authenticated();
			
 			
		}).httpBasic(Customizer.withDefaults()).formLogin(form->form.permitAll());
		
		return http.build();
	}

}
