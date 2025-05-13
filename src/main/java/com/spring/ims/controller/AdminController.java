package com.spring.ims.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ims.dto.VendorProductDTO;
import com.spring.ims.entity.Invoice;
import com.spring.ims.entity.Product;
import com.spring.ims.entity.Vendor;
import com.spring.ims.service.InvoiceService;
import com.spring.ims.service.ProductService;
import com.spring.ims.service.VendorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
    private InvoiceService invoiceService;

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
	    productService.addProduct(product);
	    vendor.setProduct(product);
	    vendorService.addVendor(vendor);
//	    product.setVendor_id(vendorService.findVendorByName(vendor.getName()).getId());
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
    @PostMapping("/vendor/delete/{id}")
    public String deleteVendor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Optional: Check if the vendor exists
    	 System.out.println("Deleting vendor with ID: " + id);
        if (vendorService.vendorExistsById(id)) {
            vendorService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Vendor deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Vendor not found.");
        }
        return "redirect:/admin/vendor/view";
    }
	 @GetMapping("/purchase")
	    public String showPurchaseForm(Model model) {
	        model.addAttribute("vendors", vendorService.viewAll());
	        return "purchaseOrder";
	    }

	    @PostMapping("/purchase/add")
	    public String handlePurchaseOrder(
	            @RequestParam Long vendorId,
	            @RequestParam int quantity,
	            @RequestParam double price,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime purchaseDate) {

	        invoiceService.createPurchaseInvoice(vendorId, quantity, price, purchaseDate);
	        return "redirect:/admin/adminHome";
	    }
	    @GetMapping("/vendor/{vendorId}/product")
	    @ResponseBody
	    public ResponseEntity<Product> getProductByVendor(@PathVariable Long vendorId) {
	        return vendorService.findById(vendorId)
	                .map(vendor -> ResponseEntity.ok(vendor.getProduct()))
	                .orElse(ResponseEntity.notFound().build());
	    }
	    @GetMapping("/invoices")
	    public String viewInvoices(Model model) {
	        List<Invoice> invoices = invoiceService.findAll();

	        // Format each invoice's dateTime to a string
	        List<Map<String, Object>> invoiceData = invoices.stream().map(invoice -> {
	            Map<String, Object> map = new HashMap<>();
	            map.put("invoice", invoice);
	            map.put("formattedDate", invoice.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
	            return map;
	        }).toList();

	        model.addAttribute("invoiceData", invoiceData);
	        return "viewInvoices";
	    }

	    @PostMapping("/invoice/delete/{id}")
	    public String deleteInvoice(@PathVariable Long id) {
	        invoiceService.deleteById(id);
	        return "redirect:/admin/invoices";
	    }
	    
	    @GetMapping("/products")
	    public String viewAllProducts(Model model) {
	        List<Product> products = productService.viewAll();
	        model.addAttribute("products", products);
	        return "viewProducts";
	    }
}
