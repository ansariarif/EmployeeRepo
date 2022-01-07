package com.neosoft.spring.boot.service;

import java.util.List;

import com.neosoft.spring.boot.model.Rental;



public interface RentalService {
	
    List<Rental> getAllRentals();
	
    Rental saveRental(Rental rental);
	
    Rental getRentalById(Long id);
	
    Rental updateRental(Rental rental);
	
	void deleteRentalById(Long id);

}
