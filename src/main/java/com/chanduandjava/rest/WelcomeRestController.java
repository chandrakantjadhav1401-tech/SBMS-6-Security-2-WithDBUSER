package com.chanduandjava.rest;

import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.web.bind.annotation.RestController
public class WelcomeRestController {
	
	
//	if i am sending the username password also nut Spring has no about information 
	
//	EveryOne Access the without login 
	@GetMapping("/welcome")
	public String welcome()
	{
		return "welcome i am chandu";
	}
	
	
	
//	only admin will get access
	@GetMapping("/admin")
	public String  admin()
	{
		return "welcome to admin";
	}
	
	
	
//	only user access 
	@GetMapping("/user")
	public String  user()
	{
		return "welcome users";
	}
	
	
	
	
//	User+admin access
	@GetMapping("/contact")
	public String  contact()
	{
		return "+9136576326478";
	}
	
	

}
