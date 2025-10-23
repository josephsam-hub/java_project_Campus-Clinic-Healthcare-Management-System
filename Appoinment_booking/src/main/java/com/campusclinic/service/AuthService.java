package com.campusclinic.service;

import com.campusclinic.dto.LoginRequest;
import com.campusclinic.dto.LoginResponse;
import com.campusclinic.dto.RegisterRequest;
import com.campusclinic.model.User;
import com.campusclinic.repository.UserRepository;
import com.campusclinic.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final JwtUtil jwtUtil;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
	                   JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}
	
	public User register(RegisterRequest request) {
		// Check if email already exists
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}
		
		// Create new user
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole("USER"); // Default role
		user.setContact_no(request.getContact_no());
		
		return userRepository.save(user);
	}
	
	public LoginResponse login(LoginRequest request) {
		// Authenticate user
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		
		// Get user details
		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new RuntimeException("User not found"));
		
		// Generate JWT token
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
		
		// Return response
		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setEmail(user.getEmail());
		response.setName(user.getName());
		response.setRole(user.getRole());
		response.setUser_id(user.getUser_id());
		
		return response;
	}
}
