package com.pescaria.api_rest.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pescaria.api_rest.domain.entity.Customer;
import com.pescaria.api_rest.domain.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class TestController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping
	public List<Customer> listAllCustomers() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if(customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
