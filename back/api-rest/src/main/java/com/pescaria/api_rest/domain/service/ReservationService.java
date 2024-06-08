package com.pescaria.api_rest.domain.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
	
	private final Double RESERVATION_PRICE = 20.00;
	
	public Reservation getReservation(Long reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new RuntimeException("reservation not found"));
	}
	
	public Reservation save(ReservationRequestDTO request) {
		Reservation newReservation = new Reservation();
		Customer customer = customerService.getCustomer(request.customerId());
		
		int qntPeople = request.qntPeople();
			
		// Setting the date and time for Brazilian Timezone
		Instant nowUtc = Instant.now();
		ZoneId americaSp = ZoneId.of("America/Sao_Paulo");
		ZonedDateTime nowAmericaSp = ZonedDateTime.ofInstant(nowUtc, americaSp);
		
		Date occDate = request.occupationDate();
		Date currentDate = Date.valueOf(nowAmericaSp.toLocalDate());
		
		Time occTime = request.occupationTime();
		Time currentTime = Time.valueOf(nowAmericaSp.toLocalTime());

		if(qntPeople < 1) {
			throw new IllegalArgumentException("Property 'qntPeople' cannot be zero or lower.");
		}

		if(!currentDate.after(occDate) && occTime.before(currentTime)) {
			throw new IllegalArgumentException("Error in 'occupationTime' or 'occupationDate'. "
					+ "Please, verify the values and try again.");
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
}
