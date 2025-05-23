package com.example.DBS.Exceptions;

public class UserNotFound extends RuntimeException {

	
	public UserNotFound(String message)
	{
		super(message);
	}
}
