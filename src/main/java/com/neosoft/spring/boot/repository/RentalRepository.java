package com.neosoft.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.spring.boot.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{
	
	//login part
	Rental findByNameAndPassword(String name , String password);

}
