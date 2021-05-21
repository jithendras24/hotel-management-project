package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.entity.Reservation;
import com.example.demo.repos.ReservationRepository;

@SpringBootApplication
public class HotelReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder builder() {
		return WebClient.builder();
	}
	
	@Bean
	public WebClient client(WebClient.Builder builder) {
		return builder.build();
	}
	
//	@Bean
//	public CommandLineRunner runner() {
//		return new CommandLineRunner() {
//			
//			@Autowired
//			private ReservationRepository repo;
//			
//			@Override
//			public void run(String... args) throws Exception {
//				this.repo.save(new Reservation(1234L, LocalDateTime.now(), 124L, 8, 1));
//			}
//		};
//	}
	
}
