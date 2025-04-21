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
        p1.setDescription("Premium wireless headphones with noise cancellation");
        p1.setPrice(129.99);
        p1.setImage("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500&q=80");
        p1.setCategory("Electronics");
        p1.setRating(4.5);
        p1.setNew(true);
        p1.setPopularity(100);
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Smart Watch");
        p2.setDescription("Fitness tracker with heart rate monitor");
        p2.setPrice(199.99);
        p2.setImage("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500&q=80");
        p2.setCategory("Electronics");
        p2.setRating(4.2);
        p2.setNew(true);
        p2.setPopularity(85);
        productRepository.save(p2);

        Product p3 = new Product();
        p3.setName("Cotton T-Shirt");
        p3.setDescription("Comfortable 100% cotton t-shirt");
        p3.setPrice(24.99);
        p3.setImage("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500&q=80");
        p3.setCategory("Clothing");
        p3.setRating(4.0);
        p3.setNew(false);
        p3.setPopularity(75);
        productRepository.save(p3);

        Product p4 = new Product();
        p4.setName("Running Shoes");
        p4.setDescription("Lightweight running shoes with cushioned sole");
        p4.setPrice(89.99);
        p4.setImage("https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500&q=80");
        p4.setCategory("Footwear");
        p4.setRating(4.7);
        p4.setNew(true);
        p4.setPopularity(95);
        productRepository.save(p4);

        Product p5 = new Product();
        p5.setName("Backpack");
        p5.setDescription("Durable backpack with laptop compartment");
        p5.setPrice(59.99);
        p5.setImage("https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=500&q=80");
        p5.setCategory("Accessories");
        p5.setRating(4.3);
        p5.setNew(false);
        p5.setPopularity(80);
        productRepository.save(p5);
    }
}