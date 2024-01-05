package com.example.dbms.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

// Production

@Controller
public class IndexController {
  @GetMapping("/")
	public String show_login(){
		return "login_page";
	}  

	@GetMapping("/signup")
	public String show_signup(){
		return "signup_page";
	}

	// @GetMapping("/home?token={token}")
	// public String show_home(@PathVariable String token) {
	// 	return "index";
	// }
}