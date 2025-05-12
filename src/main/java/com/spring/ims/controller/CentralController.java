package com.spring.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ims.entity.User;
import com.spring.ims.service.UserService;

@Controller
@RequestMapping("/")
public class CentralController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/")
	public String showRegisterUserForm(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
	
	@PostMapping("/loginVal")
	public String loginValidation(@ModelAttribute User user,Model model) {
		User usernow=userService.findByEmailAndPassword(user);
		if(usernow !=null)
		{	model.addAttribute("user",usernow); 
		return "userHome";
		}
		else if(user.getEmail().equals("admin@gmail.com")&user.getPassword().equals("admin"))
			return "adminHome";
		else
			return "redirect:/login";
		
	}
	

	
	
}
