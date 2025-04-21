package com.example.ecommerce.config;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        // Clear existing products
        productRepository.deleteAll();

        // Add sample products
        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setCategory("Electronics");
        p1.setPrice(999.99);
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Smartphone");
        p2.setCategory("Electronics");
        p2.setPrice(599.99);
        productRepository.save(p2);

        Product p3 = new Product();
        p3.setName("Headphones");
        p3.setCategory("Electronics");
        p3.setPrice(99.99);
        productRepository.save(p3);
    }
}