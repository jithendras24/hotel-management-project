package com.example.demo.exception.handlers;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	
	String message;
	LocalDateTime localDateTime;
	String description;
	
}
