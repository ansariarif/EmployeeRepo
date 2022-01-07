package com.neosoft.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.spring.boot.model.AddCar;
import com.neosoft.spring.boot.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<AddCar> getAllCars() {
		
		return carRepository.findAll();
	}

	@Override
	public AddCar saveCar(AddCar addcar) {
		
		return carRepository.save(addcar);
	}

	@Override
	public AddCar getCarByCarno(int carno) {
		
		return carRepository.findById(carno).get();
	}

	@Override
	public AddCar updateCar(AddCar addcar) {
		
		return carRepository.save(addcar);
	}

	@Override
	public void deleteCarByCarno(int carno) {
		
		carRepository.deleteById(carno);
		
	}
	
	//issued part

	@Override
	public AddCar updateIssuedStatus(AddCar addcar) {
		
	     return carRepository.save(addcar);
	}

	@Override
	public void updateIssuedStatus(int issued, int carno) {
		
		 carRepository.updateCarIssueStatus(issued, carno);
	}  

	
	
}
