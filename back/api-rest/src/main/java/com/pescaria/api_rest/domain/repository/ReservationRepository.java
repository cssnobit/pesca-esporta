package com.pescaria.api_rest.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pescaria.api_rest.domain.entity.Reservation;

public interface ReservationRepository 
					extends JpaRepository<Reservation, Long>, ReservationRepositoryQueries{
	
}
