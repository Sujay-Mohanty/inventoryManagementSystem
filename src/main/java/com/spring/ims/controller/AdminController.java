package com.spring.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ims.dto.VendorProductDTO;
import com.spring.ims.entity.Product;
import com.spring.ims.entity.Vendor;
import com.spring.ims.service.ProductService;
import com.spring.ims.service.VendorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	VendorService vendorService;

	@GetMapping("/adminHome")
	public String adminHome(){
		return "adminHome";
	}
	@GetMapping("/vendor")
	public String vendorPage() {
		return "vendor";
	}
	@GetMapping("/vendor/add")
	public String addVendorForm(Model model) {
		model.addAttribute("vendorproductDTO",new VendorProductDTO());

		
		return "vendorAdd";
	}
	
	@PostMapping("/vendor/add/save")
	public String addVendor(@ModelAttribute VendorProductDTO vpDTO)
			//@ModelAttribute("product") Product product,
//			@ModelAttribute("vendor") Vendor vendor 
	{
	    Vendor vendor = vpDTO.getVendor();
	    Product product = vpDTO.getProduct();
	    vendorService.addVendor(vendor);
	    product.setVendor_id(vendorService.findVendorByName(vendor.getName()).getId());
	    productService.addProduct(product);
		
		//product.setVendor_id(vendorService.findVendorByName(vendor.getName()).getId());
		//productService.addProduct(product);
		return "adminHome";	
		
	}
	
	@GetMapping("/vendor/view")
	public String viewVendors(Model model) {
		List<Vendor> vendors=vendorService.viewAll();
		model.addAttribute("vendors",vendors);
		return "vendorView";
	}
}
