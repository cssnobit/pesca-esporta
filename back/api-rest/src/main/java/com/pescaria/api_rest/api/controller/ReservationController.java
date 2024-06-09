package com.pescaria.api_rest.api.controller;

import java.util.List;

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
import com.pescaria.api_rest.dto.ReservationResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	
	// find all reservations by customer id
	@GetMapping("/{customerId}")
	public List<ReservationResponseDTO> listAllByCustomerId(@PathVariable Long customerId) {
		return reservationService.listAllByCustomerId(customerId);
	}
	
	@PutMapping
	public ResponseEntity<?> booking(@RequestBody ReservationRequestDTO request) {
		try {
			Reservation newReservation = reservationService.save(request);
			
			ReservationResponseDTO response = new ReservationResponseDTO(newReservation.getQntPeople(),
					newReservation.getOccupationDate(), newReservation.getOccupationTime(),
					newReservation.getStatus());

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

}
