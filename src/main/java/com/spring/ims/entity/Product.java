package com.spring.ims.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
@Table(name="imsprod")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//MYSQL uses Identity , Sequence as the Generation Type
	//For MYSQL need to use IDENTTIY as the generation type
	private long id;
	private String name;
	private String description;
	private int quantity;
	private double price;
	
	private long vendor_id;
	
}

	

	
	
	
	