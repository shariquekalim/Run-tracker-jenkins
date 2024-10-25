package com.crick.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crick.error.ErrorResponse;
import com.crick.error.UserAlreadyExistException;

@RestControllerAdvice
public class ApplicationExecptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> validationExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String,String> map= new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});
		return map;
		
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleUserAlreadyExist(UserAlreadyExistException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
		
	}

}
