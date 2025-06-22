package com.dharshan.Warehouse_Management.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle validation errors (eg:  @Valid failures)
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception){
		Map<String,Object> maperrors=new HashMap<>();
		
		exception.getBindingResult().getFieldErrors().forEach(error->
			maperrors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<>(new ErrorResponse(
				LocalDateTime.now(), 
				"Validation Failed", 
				HttpStatus.BAD_REQUEST.value(), 
				maperrors),
				HttpStatus.BAD_REQUEST );
					
		
		}
	
	
	//// Handle custom resource not found
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
	       
		 
		 return new ResponseEntity<>( new ErrorResponse(
				 LocalDateTime.now(),
				 ex.getMessage(),
				 HttpStatus.NOT_FOUND.value(),
				 null),
				 HttpStatus.NOT_FOUND);
	          
	    }
	 
	 /// // Handle insufficient stock
	 
	 @ExceptionHandler(InsufficientStockException.class)
	 public ResponseEntity<ErrorResponse> handleInsufficientStock(InsufficientStockException ex){
		 
		 return new ResponseEntity<>( new ErrorResponse(
				 LocalDateTime.now(),
				 ex.getMessage(),
				 HttpStatus.BAD_REQUEST.value(),
				 Map.of("AvailableStock",ex.getAvailableStock())),				 
				 HttpStatus.BAD_REQUEST);
		 
	 }
	  // Handle file upload errors
	 
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	 public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException ex){
		 
		 return new ResponseEntity<>( new ErrorResponse(
				 LocalDateTime.now(),
				 "File Size is Exceed",
				 HttpStatus.PAYLOAD_TOO_LARGE.value(),
				 Map.of("maxSize",ex.getMaxUploadSize()+" bytes")),
				 HttpStatus.PAYLOAD_TOO_LARGE);
				 
		 
	 }
	// Handle unauthorized access
	 
	 public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex){
		 return new ResponseEntity<>( new ErrorResponse(
				 LocalDateTime.now(),
				 "Access Denied:"+ex.getMessage(),
				 HttpStatus.FORBIDDEN.value(),
				 null ),
				HttpStatus.FORBIDDEN);
	 }
	// Fallback for all other exceptions
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
	        return new ResponseEntity<>(
	            new ErrorResponse(
	                LocalDateTime.now(),
	                "Internal server error: " + ex.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                null),
	            HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 
	 
	 

}
