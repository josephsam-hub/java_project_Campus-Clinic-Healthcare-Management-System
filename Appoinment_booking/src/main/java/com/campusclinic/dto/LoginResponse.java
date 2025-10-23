package com.campusclinic.dto;

public class LoginResponse {
	
	private String token;
	private String email;
	private String name;
	private String role;
	private Long user_id;
	
	// Default Constructor
	public LoginResponse() {
	}
	
	// Constructor with all fields
	public LoginResponse(String token, String email, String name, String role, Long user_id) {
		this.token = token;
		this.email = email;
		this.name = name;
		this.role = role;
		this.user_id = user_id;
	}
	
	// Getters and Setters
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
