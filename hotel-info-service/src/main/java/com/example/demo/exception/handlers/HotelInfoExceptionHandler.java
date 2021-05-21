package com.example.demo.exception.handlers;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HotelInfoExceptionHandler {
	
//	@ExceptionHandler(value = DuplicateKeyException.class)
//	public ErrorMessage dupIdEx(DuplicateKeyException e, WebRequest request) {
//		return new ErrorMessage(e.getMessage(), LocalDateTime.now(), request.getDescription(false));
//	}
//	
//	@ExceptionHandler(value = NoSuchElementException.class)
//	public ErrorMessage noElementEx(NoSuchElementException e, WebRequest request) {
//		return new ErrorMessage(e.getMessage(), LocalDateTime.now(), request.getDescription(false));
//	}
	
	@ExceptionHandler(value = Exception.class)
	public ErrorMessage exHandle(Exception e, WebRequest request) {
		return new ErrorMessage(e.getMessage(), LocalDateTime.now(), request.getDescription(false));
	}
}
