package com.example.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullstack.model.fooditems;

public interface fooddetails extends JpaRepository<fooditems,Long>{

	List<fooditems> findByUploadedBy(String attribute);
	
}
