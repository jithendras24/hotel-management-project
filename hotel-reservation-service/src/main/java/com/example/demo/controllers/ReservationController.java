package com.example.demo.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;

@RestController
@RefreshScope
@CrossOrigin(origins = "*")
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	@Autowired
	public WebClient client;
	
	@PostMapping(path = "/bookHotel")
	public Reservation bookHotel(@RequestBody Reservation resvDetails) {
		
		String hotelUri = "http://HOTEL-INFO-SERVICE/hotelInfo/id/" + resvDetails.getHotelId();
		System.out.println(hotelUri);
		client.get().uri(hotelUri);
		
		if(service.findById(resvDetails.getId()).isPresent()) {
			throw new DuplicateKeyException("Reservation Id already found");
		}
		
		return this.service.save(resvDetails);
	}
	
	@GetMapping(path = "/id/{id}")
	public Reservation findById(@PathVariable("id") long id) {
		
		Optional<Reservation> optRes = this.service.findById(id);
		
		if(optRes.isEmpty()) {
			throw new NoSuchElementException("no reservation found with id: " +id);		
		}
		
		return optRes.get();
	}
	
//	@GetMapping("/test/id/{id}")
//	public String test(@PathVariable("id") int id) {
//		client.get().uri("http://HOTEL-INFO-SERVICE/hotelInfo/id/"+id);
//		return "Found";
//	}
	
}
	
