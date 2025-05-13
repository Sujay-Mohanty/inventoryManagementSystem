package com.spring.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ims.entity.Product;
import com.spring.ims.entity.User;
import com.spring.ims.service.CartService;
import com.spring.ims.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/products")
    public String viewAllProducts(Model model) {
        List<Product> products = productService.viewAll();
        model.addAttribute("products", products);
        return "viewProductUser";
    }
	@GetMapping("/userHome")
	public String userHome(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("loggedInUser");
	    if (user == null) return "redirect:/login";

	    model.addAttribute("user", user);
	    return "userHome";
	}
	@PostMapping("/user/cart/add/{productId}")
	public String addToCart(@PathVariable Long productId,
	                        @RequestParam int quantity,
	                        HttpSession session,
	                        RedirectAttributes redirectAttributes) {
	    // ✅ Get user from session
	    User loggedInUser = (User) session.getAttribute("loggedInUser");

	    if (loggedInUser == null) {
	        return "redirect:/login"; // User not logged in
	    }

	    try {
	        cartService.addProductToCart(loggedInUser.getEmail(), productId, quantity);
	        redirectAttributes.addFlashAttribute("success", "Product added to cart successfully!");
	    } catch (IllegalArgumentException ex) {
	        redirectAttributes.addFlashAttribute("error", ex.getMessage());
	    }

	    return "redirect:/user/products";
	}
}
