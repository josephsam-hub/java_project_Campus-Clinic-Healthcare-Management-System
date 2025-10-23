package com.campusclinic.dto;

import java.time.LocalDateTime;

public class UserDTO {
	
	private Long user_id;
	private String name;
	private String email;
	private String role;
	private String contact_no;
	private LocalDateTime join_date;
	
	// Default Constructor
	public UserDTO() {
	}
	
	// Constructor with all fields
	public UserDTO(Long user_id, String name, String email, String role, 
	               String contact_no, LocalDateTime join_date) {
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.contact_no = contact_no;
		this.join_date = join_date;
	}
	
	// Getters and Setters
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
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
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getContact_no() {
		return contact_no;
	}
	
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	public LocalDateTime getJoin_date() {
		return join_date;
	}
	
	public void setJoin_date(LocalDateTime join_date) {
		this.join_date = join_date;
	}
}
