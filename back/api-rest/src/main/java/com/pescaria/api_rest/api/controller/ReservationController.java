package com.pescaria.api_rest.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pescaria.api_rest.domain.entity.Customer;
import com.pescaria.api_rest.domain.entity.Reservation;
import com.pescaria.api_rest.domain.entity.ReservationStatus;
import com.pescaria.api_rest.domain.repository.CustomerRepository;
import com.pescaria.api_rest.domain.repository.ReservationRepository;
import com.pescaria.api_rest.dto.ReservationRequestDTO;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<?> getReservation(@PathVariable Long reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		
		if(reservation.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(reservation.get());
	}
	
	@PutMapping
	public ResponseEntity<?> booking(@RequestBody ReservationRequestDTO request) {
		Reservation newReservation = new Reservation();
		Optional<Customer> customer = customerRepository.findById(request.customerId());

		if(customer.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		newReservation.setCustomer(customer.get());
		newReservation.setOccupationDate(request.occupationDate());
		newReservation.setOccupationTime(request.occupationTime());
		newReservation.setQntPeople(request.qntPeople());
		newReservation.setTotal(request.total());
		newReservation.setStatus(ReservationStatus.RESERVED);
		
		Reservation reservation = reservationRepository.save(newReservation);
		return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
	}

}
