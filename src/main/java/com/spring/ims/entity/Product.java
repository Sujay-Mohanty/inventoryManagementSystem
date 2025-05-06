package com.spring.ims.entity;

//import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

//@Setter
//@Getter
//@ToString
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

@Entity
@Table(name = "imsproducts")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// MYSQL uses Identity , Sequence as the Generation Type
	// For MYSQL need to use IDENTTIY as the generation type
	private long id;
	private String name;
	private String description;
	private int quantity;
	private double price;

	public Product() {
	}

	public Product(long id, String name, String description, int quantity, double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public Product(String name, String description, int quantity, double price) {
		super();
//		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
