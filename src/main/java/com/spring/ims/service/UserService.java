package com.spring.ims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void saveUserData(UserData user) {
		userRepository.save(user);
	}
	
	
}