package com.pescaria.api_rest.dto;

import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotNull;

public record ReservationRequestDTO(Integer qntPeople, Date occupationDate,
		Time occupationTime, @NotNull Long customerId) { }
