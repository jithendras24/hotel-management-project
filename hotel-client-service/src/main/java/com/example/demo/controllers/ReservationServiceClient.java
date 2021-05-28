package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
public class ReservationServiceClient {
	
	public static final String RESVURI = "lb://RESERVATION-SERVICE/reservation";
	
	@Autowired
	private WebClient client;
	
	@PostMapping("/bookHotel")
	public Mono<String> bookHotel(@RequestBody String entity) {
		
		return client.post().uri(RESVURI + "/bookHotel").contentType(MediaType.APPLICATION_JSON)
							.bodyValue(entity).retrieve().bodyToMono(String.class);
	}	
	
	@GetMapping("/id/{id}")
	public Mono<String> findById(@PathVariable int id) {
		return client.get().uri(RESVURI + "/id/" +id).retrieve().bodyToMono(String.class);
	}
	
}
