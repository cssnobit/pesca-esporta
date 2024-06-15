package com.pescaria.api_rest.domain.exception;

public class ReservationNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ReservationNotFoundException(String message) {
		super(message);
	}
	
	public ReservationNotFoundException(Long reservationId) {
		this(String.format("Reservation with id %d does not exist.", reservationId));
	}
}
