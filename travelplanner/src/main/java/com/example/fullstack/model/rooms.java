package com.example.fullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Annotating the class as a JPA Entity
@Entity
public class rooms {

    // Defining the fields for the Rooms entity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates unique IDs
    private Long id;

    private Integer roomNo;
    private String roomType;
    private Double pricePerNight;
    private Integer capacity;
    private String imageUrl;
    private String uploadedBy;

    // Default constructor
    public rooms() {
    }

    // Constructor with fields
    public rooms(Integer roomNo, String roomType, Double pricePerNight, Integer capacity, String imageUrl, String uploadedBy) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
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

    public Integer getRoomNo() {
        return roomNo;
    }

    // Correct the setter to match the field name "roomNo"
    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    // Overriding toString() for better readability
    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", roomNo=" + roomNo +
                ", roomType='" + roomType + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", capacity=" + capacity +
                ", imageUrl='" + imageUrl + '\'' +
                ", uploadedBy='" + uploadedBy + '\'' +
                '}';
    }
}
