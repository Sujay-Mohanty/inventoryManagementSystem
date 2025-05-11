package com.spring.ims.dto;

import com.spring.ims.entity.Product;
import com.spring.ims.entity.Vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendorProductDTO {
	private Vendor vendor;
	private Product product;
}
