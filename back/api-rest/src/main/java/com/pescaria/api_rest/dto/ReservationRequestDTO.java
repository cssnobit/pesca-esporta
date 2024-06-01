package com.pescaria.api_rest.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import com.pescaria.api_rest.domain.entity.ReservationStatus;

public record ReservationRequestDTO(Integer qntPeople, Date occupationDate,
		Time occupationTime, BigDecimal total, Long customerId) { }
