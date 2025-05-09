package com.spring.ims.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="imsuser")
public class User {
	@Id
	private String name;
	private String email;
	private String contact;
	private String password;

}
