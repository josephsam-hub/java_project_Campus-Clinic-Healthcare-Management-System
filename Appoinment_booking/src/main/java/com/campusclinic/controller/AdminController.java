package com.campusclinic.controller;

import com.campusclinic.model.User;
import com.campusclinic.model.Doctor;
import com.campusclinic.model.Appointment;
import com.campusclinic.service.UserService;
import com.campusclinic.service.DoctorService;
import com.campusclinic.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final DoctorService doctorService;
	
	@Autowired
	private final AppointmentService appointmentService;
	
	public AdminController(UserService userService, DoctorService doctorService, 
	                       AppointmentService appointmentService) {
		this.userService = userService;
		this.doctorService = doctorService;
		this.appointmentService = appointmentService;
	}
	
	// ==================== User Management ====================
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getById(id);
		return user.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		Optional<User> updatedUser = userService.update(id, user);
		return updatedUser.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		boolean deleted = userService.delete(id);
		if (deleted) {
			return ResponseEntity.ok("User deleted successfully");
		}
		return ResponseEntity.notFound().build();
	}
	
	// ==================== Doctor Management ====================
	
	@PostMapping("/doctors")
	public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
		Doctor createdDoctor = doctorService.create(doctor);
		return ResponseEntity.ok(createdDoctor);
	}
	
	@GetMapping("/doctors")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAll();
		return ResponseEntity.ok(doctors);
	}
	
	@GetMapping("/doctors/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		Optional<Doctor> doctor = doctorService.getById(id);
		return doctor.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/doctors/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		Optional<Doctor> updatedDoctor = doctorService.update(id, doctor);
		return updatedDoctor.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/doctors/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
		boolean deleted = doctorService.delete(id);
		if (deleted) {
			return ResponseEntity.ok("Doctor deleted successfully");
		}
		return ResponseEntity.notFound().build();
	}
	
	// ==================== Appointment Management ====================
	
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAll();
		return ResponseEntity.ok(appointments);
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
		Optional<Appointment> appointment = appointmentService.getById(id);
		return appointment.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		Optional<Appointment> updatedAppointment = appointmentService.adminUpdate(id, appointment);
		return updatedAppointment.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
		boolean deleted = appointmentService.delete(id);
		if (deleted) {
			return ResponseEntity.ok("Appointment deleted successfully");
		}
		return ResponseEntity.notFound().build();
	}
	
	// ==================== Reports ====================
	
	@GetMapping("/reports/doctor-usage")
	public ResponseEntity<List<Object[]>> getDoctorUsageReport() {
		List<Object[]> report = appointmentService.getDoctorUsageReport();
		return ResponseEntity.ok(report);
	}
	
	@GetMapping("/reports/patient-visits")
	public ResponseEntity<List<Object[]>> getPatientVisitsReport() {
		List<Object[]> report = appointmentService.getPatientVisitsReport();
		return ResponseEntity.ok(report);
	}
}
