package com.pescaria.api_rest.domain.service;

import org.springframework.stereotype.Service;

import com.pescaria.api_rest.domain.entity.Customer;
import com.pescaria.api_rest.domain.entity.Reservation;
import com.pescaria.api_rest.domain.entity.ReservationStatus;
import com.pescaria.api_rest.domain.repository.ReservationRepository;
import com.pescaria.api_rest.dto.ReservationRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final CustomerService customerService;
	
	public Reservation getReservation(Long reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new RuntimeException("reservation not found"));
	}
	
	public Reservation save(ReservationRequestDTO request) {
		Reservation newReservation = new Reservation();
		Customer customer = customerService.getCustomer(request.customerId());

		newReservation.setCustomer(customer);
		newReservation.setOccupationDate(request.occupationDate());
		newReservation.setOccupationTime(request.occupationTime());
		newReservation.setQntPeople(request.qntPeople());
		newReservation.setTotal(request.total());
		newReservation.setStatus(ReservationStatus.RESERVED);
		
		return reservationRepository.save(newReservation);
	}
}
