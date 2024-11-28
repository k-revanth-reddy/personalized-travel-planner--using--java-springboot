package com.example.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullstack.model.bookings;

public interface bookingsdetails extends JpaRepository<bookings,Long>{

    List<bookings> findByBookedBy(String loggedInUser);

    List<bookings> findByHotelOwner(String loggedInOwner);

}
