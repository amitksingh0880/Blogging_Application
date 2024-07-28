package com.spring.blogging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.blogging.payloads.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice    //convert class to exception handler
public class globalExceptionhandler {
	
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
    	String message = ex.getMessage();
    	ApiResponse apiResponse = new ApiResponse(message,false);
    	return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
	}
     @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String , String >> handleMethodArgsNotValidException(MethodArgumentNotValidException e)
	{
		Map<String , String> response = new HashMap<>();
		 e.getBindingResult().getAllErrors().forEach((objectError) -> {
			 String fieldName = ((FieldError)objectError).getField();
			 String message = objectError.getDefaultMessage();
			 response.put(fieldName,message);
		 });
		return new ResponseEntity<Map<String , String>>(response , HttpStatus.BAD_REQUEST);
	}
}
