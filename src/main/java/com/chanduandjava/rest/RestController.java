package com.chanduandjava.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chanduandjava.entity.User;
import com.chanduandjava.repository.UserRepository;


@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	
	
	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
//		id auto genreted 
//		give the username,password will give encrypted 
//		role set the developer 
		user.setPassword(encoder.encode(user.getPassword()));
		if(user.getUsername().startsWith("Chandu"))
		{
			user.setRole("ADMIN");
		}
		
		
		else {
		
			user.setRole("USER");
		}
		
		
		
		User saved = userRepository.save(user);
		
		
		return new ResponseEntity<User>(saved,HttpStatus.CREATED);
	}
}
