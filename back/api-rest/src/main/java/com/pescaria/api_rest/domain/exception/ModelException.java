package com.pescaria.api_rest.domain.exception;

public class ModelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModelException(String message) {
		super(message);
	}
	
	public ModelException(String message, Throwable cause) {
		super(message, cause);
	}
}
