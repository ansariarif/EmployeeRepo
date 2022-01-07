package com.neosoft.spring.boot.service;

import java.util.List;

import com.neosoft.spring.boot.model.Customer;



public interface CustomerService {
	
	
    List<Customer> getAllCustomers();
	
    Customer saveCustomer(Customer customer);
	
    Customer getCustomerById(Long id);
	
   // Customer updateRental(Customer rental);
	
	void deleteCustomerById(Long id);

}
