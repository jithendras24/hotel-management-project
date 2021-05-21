package com.example.demo.exception.handlers;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HotelExceptionHandler {
	
	@ExceptionHandler(value = DuplicateKeyException.class)
	public ErrorMessage dupIdEx(DuplicateKeyException e) {
		return new ErrorMessage(e.getMessage(), LocalDateTime.now());
	}
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ErrorMessage noElementEx(NoSuchElementException e) {
		return new ErrorMessage(e.getMessage(), LocalDateTime.now());
	}
	
	
}
