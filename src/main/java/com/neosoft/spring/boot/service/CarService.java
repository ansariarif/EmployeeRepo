package com.neosoft.spring.boot.service;

import java.util.List;

import com.neosoft.spring.boot.model.AddCar;


public interface CarService {
	
	List<AddCar> getAllCars();
	
	AddCar saveCar(AddCar addcar);
	
	AddCar getCarByCarno(int carno);
	
    AddCar updateCar(AddCar addcar);
    
    //issued part
    AddCar updateIssuedStatus(AddCar addcar);
    
    void updateIssuedStatus(int issued , int carno);
	
	void deleteCarByCarno(int carno);

}
