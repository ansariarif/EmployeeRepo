package com.neosoft.spring.boot.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neosoft.spring.boot.model.Rental;

import com.neosoft.spring.boot.service.RentalServiceImpl;

@Controller
public class RentalController {
	
	@Autowired
	private RentalServiceImpl rentalServiceImpl;
	
	//login part start here
	
	   @GetMapping("/loginrental")
	   public String  login(Model model) {
		  // ModelAndView mav = new ModelAndView("login");
		  // mav.addObject("rental", new Rental());
		   Rental rental = new Rental();
		   model.addAttribute("rental",rental);

		   return "login";
	   }
	   
	   @PostMapping("/loginrental")             //table
	   public String login(@ModelAttribute("rental") Rental rental,HttpServletRequest request) {
		   
		   
		    Rental oauthUser = rentalServiceImpl.login(rental.getName(), rental.getPassword());
		    System.out.println(oauthUser);
		   
		    if(oauthUser != null) {
		    	 request.getSession().setAttribute("name", oauthUser.getName());
		    	return "redirect:/cars";
		    }
		    else 
		    {
		    	return "redirect:/loginrental";
		    }
	   }
	   
	   //logout
	   @GetMapping("/logout")
		public String destroySession(HttpServletRequest request) {
			request.getSession().invalidate();
			return "redirect:/loginrental";
		}

	
	//end here
	
	@GetMapping("rentals")
	public String listRental(Model model) {
		model.addAttribute("rentals",rentalServiceImpl.getAllRentals());
		return "rentals";
	}
	
	@GetMapping("/rentals/new")
	public String createRentalForm(Model model) {
		
		Rental rental = new Rental();
		model.addAttribute("rental", rental);
		
		return "create_rental";
	}
	
	@PostMapping("/rentals")                
	public String saveRental(@ModelAttribute("rental") Rental rental) {
		
		rentalServiceImpl.saveRental(rental);
		
		return "redirect:/rentals";
	}
	
	
	@GetMapping("/rentals/edit/{id}")
	public String editRentalForm(@PathVariable Long id, Model model) {
		
		model.addAttribute("rental",rentalServiceImpl.getRentalById(id));
		
		return "edit_rental";
	}
	
	
	@PostMapping("/rentals/{id}")
	public String updaterental(@PathVariable Long id, 
			@ModelAttribute("rental") Rental rental,	Model model) {
		
		Rental existingRental = rentalServiceImpl.getRentalById(id);
		existingRental.setId(id);
		existingRental.setName(rental.getName());;
		existingRental.setEmail(rental.getEmail());
		existingRental.setPassword(rental.getPassword());;		
		existingRental.setMobile(rental.getMobile());
		rentalServiceImpl.updateRental(existingRental);
		
		return "redirect:/rentals";
	}
	
	@GetMapping("/rentals/{id}")
	public String deleterental(@PathVariable Long id) {
		
		rentalServiceImpl.deleteRentalById(id);
		
		return "redirect:/rentals";
	}	
	
	
	
	
	//logout part
	/*
	@PostMapping("/logout")
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		
		return "redirect:/";
	}  */
	

}
