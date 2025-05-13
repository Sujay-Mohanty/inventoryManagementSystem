package com.spring.ims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ims.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmailAndPassword(String email,String password);
	//Should return an optional 
	
	public Optional<User> findByName(String name);
}
