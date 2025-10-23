package com.campusclinic.controller;

import com.campusclinic.model.Doctor;
import com.campusclinic.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {
	
	@Autowired
	private final DoctorService doctorService;
	
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAll();
		return ResponseEntity.ok(doctors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		Optional<Doctor> doctor = doctorService.getById(id);
		return doctor.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/specialization/{specialization}")
	public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable String specialization) {
		List<Doctor> doctors = doctorService.getBySpecialization(specialization);
		return ResponseEntity.ok(doctors);
	}
}
