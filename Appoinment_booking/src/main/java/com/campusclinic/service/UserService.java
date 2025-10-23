package com.campusclinic.service;

import com.campusclinic.model.User;
import com.campusclinic.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User create(User user) {
		// Encrypt password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> update(Long id, User user) {
		if (userRepository.existsById(id)) {
			user.setUser_id(id);
			// If password is being updated, encrypt it
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			return Optional.of(userRepository.save(user));
		}
		return Optional.empty();
	}
	
	public Optional<User> updateProfile(String email, User updatedUser) {
		Optional<User> existingUser = userRepository.findByEmail(email);
		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setName(updatedUser.getName());
			user.setContact_no(updatedUser.getContact_no());
			
			// Update password only if provided
			if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
				user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
			}
			
			return Optional.of(userRepository.save(user));
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
