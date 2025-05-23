package com.example.DBS.InputDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginDTO {
	
	@NotBlank(message="Password cannot be null")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	@NotBlank(message="email cannot be null")
	@Email(message="email should be valid")
	private String email;

	
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
