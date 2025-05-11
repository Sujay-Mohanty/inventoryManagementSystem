package com.spring.ims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ims.entity.Vendor;
import com.spring.ims.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	public void addVendor(Vendor vendor) {
		vendorRepository.save(vendor);
		
	}
	public Vendor findVendorByName(String vendorName) {
		return vendorRepository.findByName(vendorName);
	}
}
