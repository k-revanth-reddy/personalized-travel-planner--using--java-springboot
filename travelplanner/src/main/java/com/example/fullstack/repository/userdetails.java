package com.example.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullstack.model.Users;

public interface userdetails extends JpaRepository<Users,Long>{
	
	Users findByEmail(String email);
}
