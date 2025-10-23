package com.campusclinic.repository;

import com.campusclinic.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// Find user by email (used for login and profile operations)
	Optional<User> findByEmail(String email);
	
	// Check if email exists (used for registration validation)
	boolean existsByEmail(String email);
	
	// Find users by role
	List<User> findByRole(String role);
	
	// Find users by name (search functionality)
	List<User> findByNameContainingIgnoreCase(String name);
}
