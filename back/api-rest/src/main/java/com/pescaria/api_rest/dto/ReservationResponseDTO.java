package com.pescaria.api_rest.dto;

import java.sql.Date;
import java.sql.Time;

import com.pescaria.api_rest.domain.entity.ReservationStatus;

public record ReservationResponseDTO(Integer qntPeople, Date occupationDate,
		Time occupationTime, ReservationStatus status) { }
