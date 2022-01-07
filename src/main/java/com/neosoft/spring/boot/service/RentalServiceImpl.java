package com.neosoft.spring.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.spring.boot.model.Rental;
import com.neosoft.spring.boot.repository.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {
    
	@Autowired
	private RentalRepository rentalRepository;
	
	
	//login part start here
	
	public Rental login(String name ,String password) {
		Rental rental = rentalRepository.findByNameAndPassword(name, password);
		return  rental;
	}
	
	
	
	
	//end here
	
	@Override
	public List<Rental> getAllRentals() {
		
		return rentalRepository.findAll();
	}

	@Override
	public Rental saveRental(Rental rental) {
		
		return rentalRepository.save(rental);
	}

	@Override
	public Rental getRentalById(Long id) {
		
		return rentalRepository.findById(id).get();
	}

	@Override
	public Rental updateRental(Rental rental) {
		
		return rentalRepository.save(rental);
	}

	@Override
	public void deleteRentalById(Long id) {
		
		rentalRepository.deleteById(id);
		
	}

}
