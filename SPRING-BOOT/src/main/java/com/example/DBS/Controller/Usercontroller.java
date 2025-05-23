package com.example.DBS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.DBS.Entity.Userentity;
import com.example.DBS.InputDTO.LoginDTO;
import com.example.DBS.InputDTO.UserInputDTO;
import com.example.DBS.Responses.ApiSuccess;
import com.example.DBS.Services.Userservice;

import jakarta.validation.Valid;


@RestController
public class Usercontroller {
	
	@Autowired
	Userservice userservice;
	
	
	
	//*************************************************CREATE A USER ACCOUNT **************************************************************
	@PostMapping("/createUser")
	public ResponseEntity<ApiSuccess<Userentity>> createUserAccount(@Valid @RequestBody UserInputDTO input)
	{
		Userentity u=userservice.createUserAccount(input);
		
        ApiSuccess<Userentity> response= new ApiSuccess<>();
		
		response.setStatuscode(HttpStatus.CREATED.value());
		
		response.setMessage("Account created successfully");
		
		response.setData(u);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	//********************************************LOGIN *******************************************************************
	@PostMapping("/login")
	public ResponseEntity<ApiSuccess<Userentity>> loginUser(@Valid @RequestBody LoginDTO logincredentials)
	{
        Userentity user=userservice.loginUser(logincredentials);
		
        ApiSuccess<Userentity> response= new ApiSuccess<>();
		
		response.setStatuscode(HttpStatus.OK.value());
		
		response.setMessage("Welcome-->"+user.getUsername());
		
		response.setData(user);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
