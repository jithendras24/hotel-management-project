package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceGatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator configureRoutes(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route(p -> p.path("/reservation/**").uri("lb://RESERVATION-SERVICE"))
				.route(p -> p.path("/hotel/**").uri("lb://HOTEL-INFO-SERVICE"))
				.route(p -> p.path("/review/**").uri("lb://REVIEW-SERVICE"))
				.build();
	}
	
}
