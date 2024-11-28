package com.example.fullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class fooditems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodItemName;
    private String foodType;
    private Double cost;
    private String imageUrl;

    // New field for the user who uploaded the food item
    private String uploadedBy;

    // Default constructor
    public fooditems() {}

    // Constructor with parameters, including uploadedBy
    public fooditems(String foodItemName, String foodType, Double cost, String imageUrl, String uploadedBy) {
        this.foodItemName = foodItemName;
        this.foodType = foodType;
        this.cost = cost;
        this.imageUrl = imageUrl;
        this.uploadedBy = uploadedBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    // Override toString() for better readability
    @Override
    public String toString() {
        return "FoodItems{" +
                "id=" + id +
                ", foodItemName='" + foodItemName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", cost=" + cost +
                ", imageUrl='" + imageUrl + '\'' +
                ", uploadedBy='" + uploadedBy + '\'' +
                '}';
    }
}
