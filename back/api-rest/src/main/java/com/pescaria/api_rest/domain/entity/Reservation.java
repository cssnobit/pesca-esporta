package com.pescaria.api_rest.domain.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	@Column(nullable = false)
	private Integer qntPeople;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "America/Sao_Paulo")
	@Column(nullable = false)
	private Date occupationDate;
	
	@Column(nullable = false)
	private Time occupationTime;
	
	@Column(columnDefinition = "VARCHAR(15)")
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@Column(nullable = false, scale = 2)
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customer;
}
