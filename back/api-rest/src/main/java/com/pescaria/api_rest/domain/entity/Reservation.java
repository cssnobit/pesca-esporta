package com.pescaria.api_rest.domain.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private Integer qntPeople;
	
	@Column(nullable = false)
	private Date occupationDate;
	
	@Column(nullable = false)
	private Time occupationTime;
	
	private ReservationStatus status;
	
	@Column(nullable = false, scale = 2)
	private BigDecimal total;
}