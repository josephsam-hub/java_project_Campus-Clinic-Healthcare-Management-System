package com.campusclinic.controller;

import com.campusclinic.model.User;
import com.campusclinic.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/profile")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<User> getProfile(Authentication authentication) {
		String email = authentication.getName();
		Optional<User> user = userService.getByEmail(email);
		return user.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/profile")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<User> updateProfile(Authentication authentication, @RequestBody User updatedUser) {
		String email = authentication.getName();
		Optional<User> user = userService.updateProfile(email, updatedUser);
		return user.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
