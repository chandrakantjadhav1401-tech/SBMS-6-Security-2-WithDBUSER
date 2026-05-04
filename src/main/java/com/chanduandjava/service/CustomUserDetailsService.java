package com.chanduandjava.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chanduandjava.entity.User;
import com.chanduandjava.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

//	je user DB madhe aahe Tyna Access Denar nahi Spring 
//	trr aplyala sangav lagel Spring la Hei DB Users aahe mhanun
	
	
	
//	Spring la mahit ch nahi ki yenara user db madhe aahe mhanun 

//	in this class tell to spring this is DB User and get the login 
	
//	one method of super class their
	
	
	
	
	
//	DB user give to the spring security now get the access
	
//	to go repository create the custom methods 
	@Autowired
	UserRepository repository;
	
	
	@Override                              //sign in from through
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		                                      //this is front end username                         
		Optional<User> byUsername = repository.findByUsername(username);
		if(byUsername.isPresent())
		{
			User user =byUsername.get();
			
			return org.springframework.security.core.userdetails.User.
					withUsername(user.getUsername()).// in database username 
					password(user.getPassword()).
					roles(user.getRole()).build();
		}
		
		else {
			throw new UsernameNotFoundException("User not found");
		}

		
	}
	
//	this method give user in database to spring 
//	this call the method and varify the data 
	
//	that time we have one manager 
//	that is Authentication manager 
	
	
}
