package com.pescaria.api_rest.domain.repository;

import java.util.List;

import com.pescaria.api_rest.domain.entity.Reservation;

public interface ReservationRepositoryQueries {

	List<Reservation> findAllByCustomerId(Long customerId);
}
