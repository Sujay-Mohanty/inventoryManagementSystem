package com.spring.ims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ims.entity.Cart;
import com.spring.ims.entity.Product;
import com.spring.ims.entity.User;

public interface CartRepository extends JpaRepository<Cart,Long> {
//	Optional<Cart> findByUserAndProduct(User user, Product product);
	//Old Logic Code
	
	Optional<Cart> findByUser(User user);
}
