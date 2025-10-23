package com.campusclinic.controller;

import com.campusclinic.model.Appointment;
import com.campusclinic.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
	
	@Autowired
	private final AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Appointment> createAppointment(Authentication authentication, 
	                                                     @RequestBody Appointment appointment) {
		String email = authentication.getName();
		Appointment createdAppointment = appointmentService.create(email, appointment);
		return ResponseEntity.ok(createdAppointment);
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Appointment>> getUserAppointments(Authentication authentication) {
		String email = authentication.getName();
		List<Appointment> appointments = appointmentService.getByUserEmail(email);
		return ResponseEntity.ok(appointments);
	}
	
	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
		List<Appointment> appointments = appointmentService.getByDoctorId(doctorId);
		return ResponseEntity.ok(appointments);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Appointment> getAppointmentById(Authentication authentication, 
	                                                      @PathVariable Long id) {
		String email = authentication.getName();
		Optional<Appointment> appointment = appointmentService.getByIdAndEmail(id, email);
		return appointment.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Appointment> updateAppointment(Authentication authentication, 
	                                                     @PathVariable Long id, 
	                                                     @RequestBody Appointment appointment) {
		String email = authentication.getName();
		Optional<Appointment> updatedAppointment = appointmentService.update(id, email, appointment);
		return updatedAppointment.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<String> deleteAppointment(Authentication authentication, 
	                                                @PathVariable Long id) {
		String email = authentication.getName();
		boolean deleted = appointmentService.deleteByIdAndEmail(id, email);
		if (deleted) {
			return ResponseEntity.ok("Appointment deleted successfully");
		}
		return ResponseEntity.notFound().build();
	}
}
