package com.spring.ims.service;

import java.util.List;

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
	public List<Vendor> viewAll(){
		return vendorRepository.findAll();
	}
}
