package com.example.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullstack.model.hotels;

public interface hoteldetails extends JpaRepository<hotels,Long> {

	hotels findByEmail(String email);

    List<hotels> findBycity(String to);
	
}
