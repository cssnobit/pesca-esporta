package com.pescaria.api_rest.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pescaria.api_rest.domain.entity.Reservation;
import com.pescaria.api_rest.domain.service.ReservationService;
import com.pescaria.api_rest.dto.ReservationRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
	private final ReservationService reservationService;
	
	@GetMapping("/{reservationId}")
	public ResponseEntity<?> getReservation(@PathVariable Long reservationId) {
		try {
			Reservation reservation = reservationService.getReservation(reservationId);
			return ResponseEntity.ok(reservation);
		} catch(RuntimeException e) {			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
	}
	
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
