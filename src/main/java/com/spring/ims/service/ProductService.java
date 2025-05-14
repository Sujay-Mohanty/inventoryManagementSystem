package com.spring.ims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ims.entity.Product;
import com.spring.ims.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
    ProductRepository repository;
	
	public Product addProduct(Product P) {
		return repository.save(P);}
	
	public List<Product> viewAll(){
		return repository.findAll();
		}
	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}
}
