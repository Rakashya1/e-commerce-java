package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable String id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String category,
                              @RequestParam(required = false) Double minPrice,
                              @RequestParam(required = false) Double maxPrice,
                              @RequestParam(required = false) Double minRating,
                              @RequestParam(required = false) String sort,
                              @RequestParam(required = false) String search,
                              Model model) {
        List<Product> products = productRepository.findAll();

        // Apply filters
        if (category != null && !category.isEmpty()) {
            products = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        }

        if (minPrice != null) {
            products = products.stream()
                .filter(p -> p.getPrice() >= minPrice)
                .collect(Collectors.toList());
        }

        if (maxPrice != null) {
            products = products.stream()
                .filter(p -> p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
        }

        if (minRating != null) {
            products = products.stream()
                .filter(p -> p.getRating() >= minRating)
                .collect(Collectors.toList());
        }

        // Apply search
        if (search != null && !search.isEmpty()) {
            String searchLower = search.toLowerCase();
            products = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(searchLower) ||
                           (p.getDescription() != null && p.getDescription().toLowerCase().contains(searchLower)))
                .collect(Collectors.toList());
        }

        // Apply sorting
        if (sort != null) {
            switch (sort) {
                case "price-asc":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "price-desc":
                    products.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                case "rating-desc":
                    products.sort(Comparator.comparing(Product::getRating).reversed());
                    break;
                case "popularity":
                    products.sort(Comparator.comparing(Product::getPopularity).reversed());
                    break;
            }
        }

        // Add all necessary attributes to the model
        model.addAttribute("products", products);
        model.addAttribute("categories", productRepository.findAll().stream()
            .map(Product::getCategory)
            .distinct()
            .collect(Collectors.toList()));
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedSort", sort);
        model.addAttribute("searchQuery", search);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minRating", minRating);

        return "products";
    }
}