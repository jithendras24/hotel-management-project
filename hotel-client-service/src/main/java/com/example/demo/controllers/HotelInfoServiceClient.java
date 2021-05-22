package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "*")
public class HotelInfoServiceClient {
	
	@Autowired
	private WebClient client;
	
	public static final String HOTELURI = "lb://HOTEL-INFO-SERVICE";
	
	@PostMapping("/addHotel")
	public Mono<String> addHotel(@RequestBody String entity) {
		return client.post().uri(HOTELURI + "/addHotel").contentType(MediaType.APPLICATION_JSON).bodyValue(entity)
			.retrieve().bodyToMono(String.class);
	}
	
	@GetMapping("/id/{id}")
	public Mono<String> findById(@PathVariable("id") int id) {
		return client.get().uri(HOTELURI + "/id/" +id).retrieve().bodyToMono(String.class);
	}
	
	@GetMapping("/hotels")
	public Flux<String> findAll() {
		return client.get().uri(HOTELURI + "/hotels").retrieve().bodyToFlux(String.class);
	}
	
	@GetMapping("/{hotelName}")
	public Mono<String> findByName(@PathVariable("hotelName") String name) {
		return client.get().uri(HOTELURI + "/hotel/" +name).retrieve().bodyToMono(String.class);
	}
	
	@GetMapping("/menu/{type}")
	public Flux<String> findByMenuType(@PathVariable("type") String type) {
		return client.get().uri(HOTELURI + "/menu/" +type).retrieve().bodyToFlux(String.class);
	}
	
	@GetMapping("/area/{area}")
	public Flux<String> findByArea(@PathVariable("area") String area) {
		return client.get().uri(HOTELURI + "/area/" +area).retrieve().bodyToFlux(String.class);
	}
	
	@GetMapping("/{area}/{menu}")
	public Flux<String> findByAreaAndMenu(@PathVariable("area") String area, @PathVariable("menu") String menu) {
		return client.get().uri(HOTELURI +"/"+ area +"/"+ menu).retrieve().bodyToFlux(String.class);
	}
	
}
