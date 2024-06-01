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
import com.pescaria.api_rest.domain.service.ReservationService;
import com.pescaria.api_rest.dto.ReservationRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
	private final ReservationService reservationService;
	
//	@GetMapping("/{reservationId}")
//	public ResponseEntity<?> getReservation(@PathVariable Long reservationId) {
//		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
//		
//		if(reservation.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		return ResponseEntity.ok(reservation.get());
//	}
	
	@PutMapping
	public ResponseEntity<?> booking(@RequestBody ReservationRequestDTO request) {
		try {
			Reservation newReservation = reservationService.save(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}

}
