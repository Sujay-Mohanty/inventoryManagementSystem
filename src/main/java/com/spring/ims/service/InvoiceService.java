package com.spring.ims.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ims.entity.Invoice;
import com.spring.ims.entity.Product;
import com.spring.ims.entity.Vendor;
import com.spring.ims.repository.InvoiceRepository;
import com.spring.ims.repository.ProductRepository;
import com.spring.ims.repository.VendorRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VendorRepository vendorRepository;

    public void createPurchaseInvoice(Long vendorId, int quantity, double price, LocalDateTime purchaseDate) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        Product product = vendor.getProduct();
        if (product == null) {
            throw new RuntimeException("No product associated with the selected vendor");
        }

        // Update product quantity and price
        product.setQuantity(product.getQuantity() + quantity);
        product.setPrice(price);
        productRepository.save(product);

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setPrice(price * quantity);
        invoice.setType("PURCHASE");
        invoice.setDateTime(purchaseDate);
        invoice.setProducts(Collections.singletonList(product));
        invoice.setVendor(vendor);

        invoiceRepository.save(invoice);
    }

	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}
	public void deleteById(long id) {
		invoiceRepository.deleteById(id);
	}
}
