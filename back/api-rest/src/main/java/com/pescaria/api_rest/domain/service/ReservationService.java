package com.pescaria.api_rest.domain.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

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
	private final Date currentDate;
	private final Time currentTime;
	private final Double RESERVATION_PRICE = 20.00;
	
	public Reservation getReservation(Long reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new RuntimeException("reservation not found"));
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
		if(validDate(occDate) && occTime.after(currentTime)) {
			return true;
		}
		return false;
	}
}
