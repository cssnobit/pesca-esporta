package com.pescaria.api_rest.domain.exception;

public class CustomerNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
	
	public CustomerNotFoundException(Long customerId) {
		this(String.format("Customer with id %d does not exist.", customerId));
	}
}
