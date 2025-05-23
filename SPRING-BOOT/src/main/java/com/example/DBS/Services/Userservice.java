package com.example.DBS.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.example.DBS.Entity.Userentity;
import com.example.DBS.Exceptions.AccountAlreadyExist;
import com.example.DBS.Exceptions.InvalidPassword;
import com.example.DBS.Exceptions.UserNotFound;
import com.example.DBS.InputDTO.LoginDTO;
import com.example.DBS.InputDTO.UserInputDTO;
import com.example.DBS.Repository.Userrepo;
import com.example.DBS.configs.BcryptConfig;



@Service
public class Userservice {
	
	@Autowired
	Userrepo userrepo;
	
	@Autowired
	BcryptConfig bcryptconfig;
	
	
	
	//**************************************************CREATE A USER (SIGNUP)*******************************************************
	public Userentity createUserAccount(UserInputDTO input)
	{
		//check user already exist or not
		Userentity u=userrepo.findByEmail(input.getEmail().toLowerCase());
		
		System.out.println(u);
		if(u==null)
		{
			//hash the password
			BCryptPasswordEncoder encoder = bcryptconfig.getBcrypt();
			
			Userentity user= new Userentity();
			user.setAccountcreatedat(LocalDateTime.now());
			user.setEmail(input.getEmail().toLowerCase());
			user.setPassword(encoder.encode(input.getPassword()));
			user.setShopname(input.getShopname());
			user.setUsername(input.getUsername().toUpperCase());
			
			return userrepo.save(user);
					
		}else {
			//throw error if user do not exist
			throw new AccountAlreadyExist("account already exist Kindly Login");
		}
	}
	
	//**************************************************LOGIN USER************************************************************
	public Userentity loginUser(LoginDTO credentials)
	{
		//check first if the user exist or not
		Userentity user=userrepo.findByEmail(credentials.getEmail());
		if(user!=null)
		{
			
			
			BCryptPasswordEncoder encoder = bcryptconfig.getBcrypt();
			
			//check the password
			if(encoder.matches(credentials.getPassword(), user.getPassword()))
			{
				return user;
			}
			else {
				//if password is wrong
				throw new InvalidPassword("password is wrong plz check..");
			}
		}
		//throw error if user not avaialable
		
		throw new UserNotFound("account do not exist kindly sign up");
		
		
	}
	
	

}
