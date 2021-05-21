package com.example.demo.entity;

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

@Document(collection = "hotelsInfo")
public class HotelInfo {
	
	@Id
	int id;
	String name;
	String serviceArea;
	String email;
	String menuType;
	String openingHours;
	
}
