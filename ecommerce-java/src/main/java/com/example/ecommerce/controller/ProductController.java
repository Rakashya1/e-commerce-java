package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String category,
                              @RequestParam(required = false) Double price,
                              Model model) {
        List<Product> products;
        if (category != null) {
            products = productRepository.findByCategory(category);
        } else if (price != null) {
            products = productRepository.findByPriceLessThan(price);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        return "products";
    }
}