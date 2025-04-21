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
        p1.setName("Wireless Headphones");
        p1.setDescription("High-quality wireless headphones with noise cancellation");
        p1.setCategory("Electronics");
        p1.setPrice(129.99);
        p1.setImage("/images/headphones.jpg");
        p1.setRating(4.5);
        p1.setNew(true);
        p1.setPopularity(120);
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Smart Watch");
        p2.setDescription("Modern smartwatch with health tracking features");
        p2.setCategory("Electronics");
        p2.setPrice(199.99);
        p2.setImage("/images/smartwatch.jpg");
        p2.setRating(4.2);
        p2.setNew(true);
        p2.setPopularity(85);
        productRepository.save(p2);

        Product p3 = new Product();
        p3.setName("Backpack");
        p3.setDescription("Durable backpack with laptop compartment");
        p3.setCategory("Accessories");
        p3.setPrice(59.99);
        p3.setImage("/images/backpack.jpg");
        p3.setRating(4.3);
        p3.setNew(false);
        p3.setPopularity(95);
        productRepository.save(p3);

        Product p4 = new Product();
        p4.setName("Sunglasses");
        p4.setDescription("Classic design sunglasses with UV protection");
        p4.setCategory("Accessories");
        p4.setPrice(79.99);
        p4.setImage("/images/sunglasses.jpg");
        p4.setRating(4.1);
        p4.setNew(false);
        p4.setPopularity(75);
        productRepository.save(p4);
    }
}