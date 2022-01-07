package com.neosoft.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.spring.boot.model.Customer;
import com.neosoft.spring.boot.model.Rental;
import com.neosoft.spring.boot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	
	//login part
	
	public Customer login(String name ,String password) {
		Customer customer = customerRepo.findByNameAndPassword(name, password);
		return  customer;
	}
	
	
	@Autowired 
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Long id) {
		
		return customerRepository.findById(id).get();
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
		
	}

}
