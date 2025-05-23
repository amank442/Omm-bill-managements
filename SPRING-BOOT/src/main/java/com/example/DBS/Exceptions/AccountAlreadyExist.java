package com.example.DBS.Exceptions;

public class AccountAlreadyExist extends RuntimeException {
	
	
	public AccountAlreadyExist(String message)
	{
		super(message);
	}

}
