package com.pescaria.api_rest.domain.service;

import org.springframework.stereotype.Service;

import com.pescaria.api_rest.domain.entity.Customer;
import com.pescaria.api_rest.domain.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	public Customer getCustomer(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}
}
