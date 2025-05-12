package com.spring.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ims.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmailAndPassword(String email,String password);
	//Should return an optional 
}
