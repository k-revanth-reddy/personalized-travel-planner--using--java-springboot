package com.example.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fullstack.model.rooms;

public interface roomdetails extends JpaRepository<rooms,Long>{

	List<rooms> findByUploadedBy(String uploadedBy);

}
