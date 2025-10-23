package com.campusclinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	
	@NotBlank(message = "Name is required")
	@Size(max = 50, message = "Name cannot exceed 50 characters")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	
	@Size(max = 20, message = "Contact number cannot exceed 20 characters")
	private String contact_no;
	
	// Default Constructor
	public RegisterRequest() {
	}
	
	// Constructor with all fields
	public RegisterRequest(String name, String email, String password, String contact_no) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact_no = contact_no;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getContact_no() {
		return contact_no;
	}
	
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
}
