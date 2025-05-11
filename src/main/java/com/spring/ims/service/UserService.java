package com.spring.ims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ims.entity.User;
import com.spring.ims.repository.UserRepository;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public User findByEmailAndPassword(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
	}
	
	
}