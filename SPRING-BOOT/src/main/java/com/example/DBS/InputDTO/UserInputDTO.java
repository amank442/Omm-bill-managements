package com.example.DBS.InputDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserInputDTO {
	
	
	@NotBlank(message="can,t be null or empty after trim")
	@Size(min=4,max=15,message ="name should be between 4 and 15")
	private String username;
	
	
	@NotBlank(message="Can't be null or empty after trim")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	@NotBlank(message="cannot be null")
	@Email(message="email should be valid")
	private String email;
	
	@NotBlank(message="can,t be null or empty after trim")
	@Size(min=8,max=25,message ="name should be between 5 and 15")
	private String shopname;
	
	

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

}
