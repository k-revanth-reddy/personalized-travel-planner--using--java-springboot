package com.example.fullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates unique IDs
    private Long id;

    private Integer roomNo; // Room number being booked
    private String roomType; // Type of the room (e.g., Deluxe, Suite)
    private Integer noOfGuests; // Number of guests

    private LocalDate checkInDate; // Check-in date
    private LocalDate checkOutDate; // Check-out date

    private String bookedBy; // Email or name of the person booking the room
    private String hotelOwner; // Email or name of the hotel owner
    private Double totalPrice; // Total price for the booking
    private String status = "Pending"; // Booking status, defaults to "Pending"

    // Default constructor (needed by JPA)
    public bookings() {}

    // Constructor with fields
    public bookings(Integer roomNo, String roomType, Integer noOfGuests, LocalDate checkInDate, LocalDate checkOutDate, 
                    String bookedBy, String hotelOwner, Double totalPrice, String status) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.noOfGuests = noOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookedBy = bookedBy;
        this.hotelOwner = hotelOwner;
        this.totalPrice = totalPrice;
        this.status = status;
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

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(Integer noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getHotelOwner() {
        return hotelOwner;
    }

    public void setHotelOwner(String hotelOwner) {
        this.hotelOwner = hotelOwner;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override toString() for better debugging
    @Override
    public String toString() {
        return "Bookings{" +
                "id=" + id +
                ", roomNo=" + roomNo +
                ", roomType='" + roomType + '\'' +
                ", noOfGuests=" + noOfGuests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", bookedBy='" + bookedBy + '\'' +
                ", hotelOwner='" + hotelOwner + '\'' +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
