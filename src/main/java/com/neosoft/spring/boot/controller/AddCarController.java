package com.neosoft.spring.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neosoft.spring.boot.model.AddCar;
import com.neosoft.spring.boot.model.Rental;
import com.neosoft.spring.boot.repository.CarRepository;
import com.neosoft.spring.boot.service.CarServiceImpl;

@Controller
public class AddCarController {
	
	
	@Autowired
	private CarServiceImpl carServiceImpl;
    
	@Autowired
	private CarRepository repo;
	
	@GetMapping("/cars")
	public String listCar(Model model,HttpSession session) {
		
		if(session.getAttribute("name") == null) {
			return "redirect:/loginrental";
		}
		
		model.addAttribute("cars", carServiceImpl.getAllCars());
		
		return "cars";
	}
	
	@GetMapping("/cars/new")
	public String createCarForm(Model model) {
		
		AddCar addcar = new AddCar();
		model.addAttribute("addcar", addcar);
		
		return "create_car";
	}
	
	@PostMapping("/cars")
	public String saveCar(@ModelAttribute("addcar") AddCar addcar) {
		
		carServiceImpl.saveCar(addcar);
		
		return "redirect:/cars";
	}
	
	
/*	@GetMapping("cars/issued/{carno}")
	public String issuedCar(@PathVariable int carno,@ModelAttribute("car") AddCar addcar,Model model) {
		AddCar setCar = carServiceImpl.getCarByCarno(carno);
		    
		    setCar.setIssued(addcar.getIssued()+1);
		    carServiceImpl.updateCar(setCar);
	   
	   return "redirect:/cars"; 	
	
	} */
	
/*	@PostMapping("/cars/{carno}")
	public String updateIssuedStatus(@PathVariable int carno,
			@ModelAttribute("car") AddCar addcar, Model model) {
		AddCar existingCar = carServiceImpl.getCarByCarno(carno);
		existingCar.setIssued(addcar.getIssued());
		carServiceImpl.updateCar(existingCar);
		return "redirect:/cars";
	}  */
	
	
	// issued car  
	
	@GetMapping("/cars/edit/{carno}")
	public String BookCarIssued(@PathVariable int carno, Model model,RedirectAttributes redirAttrs) {
		
		//issue part
		int carstatus = repo.getIssuedCarValue(carno);
		
		int carquantity = repo.getCarQuantityValue(carno);
		
		if(carquantity > carstatus) {
		     carServiceImpl.updateIssuedStatus(carstatus+1, carno);
		     redirAttrs.addFlashAttribute("success", "Car Booked! Successfully...");
		}
		else {
			redirAttrs.addFlashAttribute("error", "Sorry ! this car is out of service.");
			return "redirect:/cars";
		}
		
		//return part
		//String returnstatus =  repo.getReturnCarValue(carno);
		repo.returnIssuedCar("no", carno);
		
		
		return "redirect:/cars";
	} 
	
	
	//return car
	
	@GetMapping("/cars/return/{carno}")
	public String ReturnIssuedCar(@PathVariable int carno , Model model,RedirectAttributes redirAttrs) {
		
		int carReturn = repo.getIssuedCarValue(carno);
		
		if(carReturn > 0) {
		     carServiceImpl.updateIssuedStatus(carReturn-1, carno);
		     redirAttrs.addFlashAttribute("success", "Car Return Successfully...");
		}
		
		//return part
		
		//int returnstatus = repo.getIssuedCarValue(carno);
		repo.returnIssuedCar("yes", carno);
		
		return "redirect:/cars";
	}
	
	
/*	
	@PostMapping("/cars/{carno}")
	public String updatecar(@PathVariable int carno, 
			@ModelAttribute("car") AddCar addcar,	Model model) {
		
		AddCar existingCar = carServiceImpl.getCarByCarno(carno);
		existingRental.setId(id);
		existingRental.setName(rental.getName());;
		existingRental.setEmail(rental.getEmail());
		existingRental.setPassword(rental.getPassword());;		
		existingRental.setMobile(rental.getMobile());
		rentalServiceImpl.updateRental(existingRental);
		
		return "redirect:/rentals";
	} */
	
	@GetMapping("/cars/{carno}")
	public String deletecar(@PathVariable int carno) {
		
		carServiceImpl.deleteCarByCarno(carno);
		
		return "redirect:/cars";
	}
	
	
	//issued part
	/*
	@GetMapping("/cars/edit/{carno}")
	public String editCarForm(@PathVariable int carno, Model model) {
		
		model.addAttribute("car",carServiceImpl.getCarByCarno(carno));
		
		return "edit_car";
	}
	
	@PostMapping("/cars/{carno}")
	public String updateCar(@PathVariable int carno, 
			@ModelAttribute("car") AddCar addcar,	Model model) {
		
		AddCar existingCar = carServiceImpl.getCarByCarno(carno);
        existingCar.setCarno(carno);
        //existingCar.setName(addcar.getName());
       // existingCar.setType(addcar.getType());
       // existingCar.setQuantity(addcar.getQuantity());
		existingCar.setIssued(2);
	
		carServiceImpl.updateCar(existingCar);
		
		return "redirect:/cars";
	}
	*/
	
	
	//page link from to another
	

	//logout part
	
	
	
	
	
	

}
