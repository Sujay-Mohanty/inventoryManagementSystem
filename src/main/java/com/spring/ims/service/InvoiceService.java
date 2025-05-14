package com.spring.ims.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ims.entity.Cart;
import com.spring.ims.entity.CartItem;
import com.spring.ims.entity.Invoice;
import com.spring.ims.entity.InvoiceItem;
import com.spring.ims.entity.Product;
import com.spring.ims.entity.User;
import com.spring.ims.entity.Vendor;
import com.spring.ims.repository.CartRepository;
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
    
    @Autowired
    private CartRepository cartRepository;

    public void createPurchaseInvoice(Long vendorId, int quantity, double price, LocalDateTime purchaseDate) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        Product product = vendor.getProduct();
        if (product == null) {
            throw new RuntimeException("No product associated with the selected vendor");
        }

        // Update product quantity and price
        product.setQuantity(product.getQuantity() + quantity);
        product.setPrice(price); // assuming price update is part of vendor supplying it
        productRepository.save(product);

        // Create Invoice
        Invoice invoice = new Invoice();
        invoice.setType("PURCHASE");
        invoice.setVendor(vendor);
        invoice.setDateTime(purchaseDate);
        invoice.setPrice(quantity * price); // total invoice price

        // Create InvoiceItem
        InvoiceItem item = new InvoiceItem();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setUnitPrice(price);
        item.setInvoice(invoice); // associate with invoice

        // Add item to invoice
        invoice.setItems(Collections.singletonList(item));

        // Save invoice (cascade will save InvoiceItem too)
        invoiceRepository.save(invoice);
    }
    public void createSalesInvoice(User user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + user.getName()));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }

        Invoice invoice = new Invoice();
        invoice.setType("SALES");
        invoice.setUser(user);
        invoice.setDateTime(LocalDateTime.now());

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            //LOGICAL FLAW: ALREADY REDUCED QUANTITY WHEN ADDED TO CART
            
//            if (product.getQuantity() < quantity) {
//                throw new RuntimeException("Insufficient stock for product: " + product.getName());
//            }
//
//            // Reduce stock
//            product.setQuantity(product.getQuantity() - quantity);
//            productRepository.save(product);

            InvoiceItem item = new InvoiceItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getPrice());
            item.setInvoice(invoice);

            invoiceItems.add(item);
            totalPrice += product.getPrice() * quantity;
        }

        invoice.setPrice(totalPrice);
        invoice.setItems(invoiceItems);

        invoiceRepository.save(invoice); // Cascade saves InvoiceItem too

        // Clear the cart
        cart.getItems().clear();
        cartRepository.save(cart);
    }


	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}
	public void deleteById(long id) {
		invoiceRepository.deleteById(id);
	}
	public List<Invoice> findByUserAndType(User user, String type) {
		return invoiceRepository.findAllByUserAndType( user,type);

	}
}
