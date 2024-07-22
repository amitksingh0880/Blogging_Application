package com.spring.blogging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.blogging.payloads.ApiResponse;

@RestControllerAdvice    //convert class to exception handler
public class globalExceptionhandler {
	
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
    	String message = ex.getMessage();
    	ApiResponse apiResponse = new ApiResponse(message,false);
    	return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
	}
}
