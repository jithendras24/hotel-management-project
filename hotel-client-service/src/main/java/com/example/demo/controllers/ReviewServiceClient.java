package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
public class ReviewServiceClient {

	@Autowired
	private WebClient client;
	
	public static final String REVIEWURI = "lb://REVIEW-SERVICE";
	
	@PostMapping("/addReview")
	public Mono<String> postReview(@RequestBody String entity) {
		return client.post().uri(REVIEWURI + "/addReview").contentType(MediaType.APPLICATION_JSON).bodyValue(entity)
				.retrieve().bodyToMono(String.class);
	}
	
	@GetMapping("reviews")
	public Flux<String> findAll() {
		return client.get().uri(REVIEWURI + "/reviews").retrieve().bodyToFlux(String.class);
	}
	
	@GetMapping("id/{id}")
	public Mono<String> findById(@PathVariable("id") int id) {
		return client.get().uri(REVIEWURI + "/id/" +id).retrieve().bodyToMono(String.class);
	}
	
	@PutMapping("/update/{id}")
	public Mono<String> updateReview(@PathVariable("id") int id, @RequestBody String entity) {
		return client.put().uri(REVIEWURI + "/update/" +id).contentType(MediaType.APPLICATION_JSON).bodyValue(entity)
				.retrieve().bodyToMono(String.class);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<String> deleteReview(@PathVariable("id") int id) {
		return client.delete().uri(REVIEWURI + "/delete/" +id).retrieve().bodyToMono(String.class);
	}
	
}