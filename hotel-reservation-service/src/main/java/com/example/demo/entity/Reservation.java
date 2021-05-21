package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Document(collection = "reservationDetails")
public class Reservation {

	@Id
	Long id;
	
	LocalDateTime reservationTime;
	Long userId;
	int partySize;
	int hotelId;
	
}
