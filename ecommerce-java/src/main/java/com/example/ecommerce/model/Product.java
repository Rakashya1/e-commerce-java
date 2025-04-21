package com.example.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document("products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String category;
    private double rating;
    private boolean isNew;
    private int popularity;
    private Date createdAt;
    private Date updatedAt;

    public Product() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.rating = 0.0;
        this.isNew = false;
        this.popularity = 0;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public boolean isNew() { return isNew; }
    public void setNew(boolean isNew) { this.isNew = isNew; }
    
    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) { this.popularity = popularity; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}