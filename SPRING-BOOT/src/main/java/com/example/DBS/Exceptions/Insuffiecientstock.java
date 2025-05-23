package com.example.DBS.Exceptions;

public class Insuffiecientstock extends RuntimeException {
        
	public Insuffiecientstock(String message)
	{
		super(message);
	}
}
