package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reservation;
import com.example.demo.repos.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repo;
	
	public Reservation save(Reservation resvDetails) {
		return this.repo.save(resvDetails);
	}
	
	public Optional<Reservation> findById(long id) {
		return this.repo.findById(id);
	}

}
