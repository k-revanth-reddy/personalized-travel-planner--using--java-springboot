package com.example.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullstack.model.restaurants;

public interface restaurantdetails extends JpaRepository<restaurants,Long> {

	restaurants findByEmail(String email);

    List<restaurants> findBycity(String to);
	
}
