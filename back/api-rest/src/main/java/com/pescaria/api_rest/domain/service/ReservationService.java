package com.pescaria.api_rest.domain.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pescaria.api_rest.domain.entity.Customer;
import com.pescaria.api_rest.domain.entity.Reservation;
import com.pescaria.api_rest.domain.entity.ReservationStatus;
import com.pescaria.api_rest.domain.exception.ReservationNotFoundException;
import com.pescaria.api_rest.domain.repository.ReservationRepository;
import com.pescaria.api_rest.dto.ReservationRequestDTO;
import com.pescaria.api_rest.dto.ReservationResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final CustomerService customerService;
	private final Date currentDate;
	private final Time currentTime;
	private final Double RESERVATION_PRICE = 30.00;
	
	public Reservation getReservation(Long reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new ReservationNotFoundException(reservationId));
	}
	
	public List<ReservationResponseDTO> listAllByCustomerId(Long customerId) {
		List<Reservation> reservations = reservationRepository.findAllByCustomerId(customerId);
		List<ReservationResponseDTO> response = new ArrayList<>();
		reservations.forEach(reservation -> {
			response.add(new ReservationResponseDTO(reservation.getQntPeople(),
					reservation.getOccupationDate(),
					reservation.getOccupationTime(),
					reservation.getTotal(),
					reservation.getStatus()));
		});
		
		return response;
	}
	
	public Reservation save(ReservationRequestDTO request) {
		Reservation newReservation = new Reservation();
		Customer customer = customerService.getCustomer(request.customerId());
		
		int qntPeople = request.qntPeople();

		Date occDate = request.occupationDate();
		
		Time occTime = request.occupationTime();

		if(qntPeople < 1) {
			throw new IllegalArgumentException("Property 'qntPeople' cannot be zero or lower.");
		}

		if(!validDate(occDate)) {
			throw new IllegalArgumentException("Property 'occupationDate' cannot be before of today");
		} else if(!validDateTime(occDate, occTime)) {
			throw new IllegalArgumentException("Property 'occupationTime' cannot be before of now");
		}

		BigDecimal totalCost = new BigDecimal(qntPeople * RESERVATION_PRICE);

		newReservation.setCustomer(customer);
		newReservation.setOccupationDate(occDate);
		newReservation.setOccupationTime(occTime);
		newReservation.setQntPeople(qntPeople);
		newReservation.setTotal(totalCost);
		
		newReservation.setStatus(ReservationStatus.RESERVED);
		
		return reservationRepository.save(newReservation);
	}
	
	private boolean validDate(Date occDate) {
		if(occDate.before(currentDate)) {
			return false;
		}
		return true;
	}
	
	private boolean validDateTime(Date occDate, Time occTime) {
		if(occDate.equals(currentDate) && occTime.before(currentTime)) {
			return false;
		}
		return true;
	}
}
