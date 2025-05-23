package com.example.DBS.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.DBS.Responses.ErrorResponse;



@RestControllerAdvice
public class GlobalException {
	
	
	
	@ExceptionHandler(AccountAlreadyExist.class)
	public ResponseEntity<ErrorResponse> handleAccountExist( AccountAlreadyExist ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("Conflict");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.CONFLICT.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler(InvalidPassword.class)
	public ResponseEntity<ErrorResponse> handleInvalidPassword( InvalidPassword ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("Password invalid");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.UNAUTHORIZED.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound( UserNotFound ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("user not found");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NoFeildsProvided.class)
	public ResponseEntity<ErrorResponse> handleNoFeilds(NoFeildsProvided ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("no feilds avaialable");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.BAD_REQUEST.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ItemNotFound.class)
	public ResponseEntity<ErrorResponse> handleItemNotFound(ItemNotFound ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("item not found");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Insuffiecientstock.class)
	public ResponseEntity<ErrorResponse> handleInsuffiecientstock(Insuffiecientstock ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("Insuffiect stock");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.CONFLICT.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(UnauthorizedItemAcces.class)
	public ResponseEntity<ErrorResponse> handleInsuffiecientstock(UnauthorizedItemAcces ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("User dont own this item ");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.FORBIDDEN.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException( Exception ex)
	{
		ErrorResponse response= new ErrorResponse();
		
		response.setError("Something went wrong");
		response.setMessage(ex.getMessage());
		response.setStatuscode(HttpStatus.BAD_REQUEST.value());
		response.setTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}

}
