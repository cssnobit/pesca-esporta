package com.pescaria.api_rest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pescaria.api_rest.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
