package com.pescaria.api_rest.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pescaria.api_rest.domain.entity.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ReservationRepositoryImpl implements ReservationRepositoryQueries {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Reservation> findAllByCustomerId(Long customerId) {
		var jpql = "from Reservation r join Customer c on c.id = r.customer.id "
				+ "where c.id = :customerId";
		
		
		return em.createQuery(jpql, Reservation.class)
				.setParameter("customerId", customerId)
				.getResultList();
	}
}
