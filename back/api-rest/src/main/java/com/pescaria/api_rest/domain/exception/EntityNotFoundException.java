package com.pescaria.api_rest.domain.exception;

public class EntityNotFoundException extends ModelException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}

}
