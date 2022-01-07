package com.neosoft.spring.boot.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.neosoft.spring.boot.model.Customer;

import com.neosoft.spring.boot.service.CustomerServiceImpl;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl custoSer;
	
	
	//login part
	
	   @GetMapping("/logincustomer")
	   public ModelAndView login() {
		   ModelAndView mav = new ModelAndView("logincustomer");
		   mav.addObject("customer", new Customer());
		   return mav;
	   }
	   
	   @PostMapping("/logincustomer")             //table
	   public String login(@ModelAttribute("customer") Customer customer) {
		   Customer oauthUser = custoSer.login(customer.getName(), customer.getPassword());
		    System.out.println(oauthUser);
		    if(Objects.nonNull(oauthUser)) {
		    	return "redirect:/viewcars";
		    }
		    else 
		    {
		    	return "redirect:/logincustomer";
		    }
	   }
	
	
	
	
	//end here
	
	
	
	@GetMapping("/customers")
	public String  listCustomer(Model model) {
		
		
		model.addAttribute("customers",custoSer.getAllCustomers());
		
		
		
		return "customers";
	}  
	
	
	
	//filtering
	
	
	
	/*@GetMapping("/customers")  
	  public  MappingJacksonValue  getAllCustomer(Model  model){
		  
		// List<Candidate> candidate = new ArrayList<Candidate>();
	      model.addAttribute("customers",custoSer.getAllCustomers());
	      
	      
		  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","email","mobile");
		  FilterProvider filters = new SimpleFilterProvider().addFilter("customerfilter", filter);
		  
		  MappingJacksonValue mapping = new MappingJacksonValue(custoSer);
		  mapping.setFilters(filters);
		  return mapping;
		 
		  
	  }  */
	
	
	
	
	@GetMapping("/customers/new")
	public String createCustomerForm(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "create_customer";
	}
	
	@PostMapping("/customers")                
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		custoSer.saveCustomer(customer);
		
		return "redirect:/customers";
	}
	
	
	@GetMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		
		custoSer.deleteCustomerById(id);
		
		return "redirect:/customers";
	
	}
}
