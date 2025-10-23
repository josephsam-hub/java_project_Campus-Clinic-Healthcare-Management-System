package com.campusclinic.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	@NotBlank(message = "Name is required")
	@Size(max = 50, message = "Name cannot exceed 50 characters")
	@Column(nullable = false, length = 50)
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	@Column(nullable = false, length = 255)
	private String password;
	
	@NotBlank(message = "Role is required")
	@Column(nullable = false, length = 20)
	private String role; // "ADMIN" or "USER"
	
	@Size(max = 20, message = "Contact number cannot exceed 20 characters")
	@Column(length = 20)
	private String contact_no;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime join_date;
	
	// Default Constructor
	public User() {
	}
	
	// Constructor without ID (for registration)
	public User(String name, String email, String password, String role, String contact_no) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contact_no = contact_no;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	// UserDetails interface methods (for Spring Security)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}
	
	@Override
	public String getUsername() {
		return email; // Using email as username
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
