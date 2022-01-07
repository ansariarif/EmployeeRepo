package com.neosoft.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.spring.boot.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByNameAndPassword(String name , String password);

}
