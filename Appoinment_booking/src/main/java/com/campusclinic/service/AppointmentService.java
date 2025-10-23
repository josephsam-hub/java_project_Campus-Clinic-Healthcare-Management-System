package com.campusclinic.service;

import com.campusclinic.model.Appointment;
import com.campusclinic.model.User;
import com.campusclinic.model.Doctor;
import com.campusclinic.repository.AppointmentRepository;
import com.campusclinic.repository.UserRepository;
import com.campusclinic.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
	
	@Autowired
	private final AppointmentRepository appointmentRepository;
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final DoctorRepository doctorRepository;
	
	public AppointmentService(AppointmentRepository appointmentRepository, 
	                          UserRepository userRepository, 
	                          DoctorRepository doctorRepository) {
		this.appointmentRepository = appointmentRepository;
		this.userRepository = userRepository;
		this.doctorRepository = doctorRepository;
	}
	
	public Appointment create(String email, Appointment appointment) {
		// Get user by email
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("User not found"));
		
		// Validate doctor exists
		Doctor doctor = doctorRepository.findById(appointment.getDoctor().getDoctor_id())
			.orElseThrow(() -> new RuntimeException("Doctor not found"));
		
		// Validate appointment time is within doctor's availability
		validateAppointmentTime(doctor, appointment.getAppointment_time());
		
		// Check for double booking
		if (isDoubleBooking(doctor.getDoctor_id(), appointment.getAppointment_date(), 
		                    appointment.getAppointment_time())) {
			throw new RuntimeException("This time slot is already booked");
		}
		
		// Set user and doctor
		appointment.setUser(user);
		appointment.setDoctor(doctor);
		appointment.setStatus("Scheduled");
		
		return appointmentRepository.save(appointment);
	}
	
	public List<Appointment> getAll() {
		return appointmentRepository.findAll();
	}
	
	public Optional<Appointment> getById(Long id) {
		return appointmentRepository.findById(id);
	}
	
	public List<Appointment> getByUserEmail(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("User not found"));
		return appointmentRepository.findByUserId(user.getUser_id());
	}
	
	public List<Appointment> getByDoctorId(Long doctorId) {
		return appointmentRepository.findByDoctorId(doctorId);
	}
	
	public Optional<Appointment> getByIdAndEmail(Long id, String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("User not found"));
		
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		
		// Check if user is admin or owns the appointment
		if (appointment.isPresent()) {
			if (user.getRole().equals("ADMIN") || 
			    appointment.get().getUser().getUser_id().equals(user.getUser_id())) {
				return appointment;
			}
		}
		return Optional.empty();
	}
	
	public Optional<Appointment> update(Long id, String email, Appointment updatedAppointment) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("User not found"));
		
		Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
		
		if (existingAppointment.isPresent()) {
			Appointment appointment = existingAppointment.get();
			
			// Check if user owns the appointment or is admin
			if (!user.getRole().equals("ADMIN") && 
			    !appointment.getUser().getUser_id().equals(user.getUser_id())) {
				throw new RuntimeException("Unauthorized to update this appointment");
			}
			
			// Update only allowed fields for users
			if (user.getRole().equals("USER")) {
				// Users can update date, time, and reason
				if (updatedAppointment.getAppointment_date() != null) {
					appointment.setAppointment_date(updatedAppointment.getAppointment_date());
				}
				if (updatedAppointment.getAppointment_time() != null) {
					appointment.setAppointment_time(updatedAppointment.getAppointment_time());
				}
				if (updatedAppointment.getReason() != null) {
					appointment.setReason(updatedAppointment.getReason());
				}
				if (updatedAppointment.getDoctor() != null) {
					Doctor doctor = doctorRepository.findById(updatedAppointment.getDoctor().getDoctor_id())
						.orElseThrow(() -> new RuntimeException("Doctor not found"));
					appointment.setDoctor(doctor);
				}
			}
			
			// Validate appointment time
			validateAppointmentTime(appointment.getDoctor(), appointment.getAppointment_time());
			
			return Optional.of(appointmentRepository.save(appointment));
		}
		return Optional.empty();
	}
	
	public Optional<Appointment> adminUpdate(Long id, Appointment updatedAppointment) {
		if (appointmentRepository.existsById(id)) {
			updatedAppointment.setAppointment_id(id);
			return Optional.of(appointmentRepository.save(updatedAppointment));
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		if (appointmentRepository.existsById(id)) {
			appointmentRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean deleteByIdAndEmail(Long id, String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("User not found"));
		
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		
		if (appointment.isPresent()) {
			// Check if user owns the appointment or is admin
			if (user.getRole().equals("ADMIN") || 
			    appointment.get().getUser().getUser_id().equals(user.getUser_id())) {
				appointmentRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}
	
	// ==================== Validation Methods ====================
	
	private void validateAppointmentTime(Doctor doctor, LocalTime appointmentTime) {
		if (appointmentTime.isBefore(doctor.getAvailable_from()) || 
		    appointmentTime.isAfter(doctor.getAvailable_to())) {
			throw new RuntimeException("Appointment time is outside doctor's available hours");
		}
	}
	
	private boolean isDoubleBooking(Long doctorId, LocalDate date, LocalTime time) {
		List<Appointment> existingAppointments = appointmentRepository
			.findByDoctorIdAndAppointmentDateAndTime(doctorId, date, time);
		return !existingAppointments.isEmpty();
	}
	
	// ==================== Report Methods ====================
	
	public List<Object[]> getDoctorUsageReport() {
		return appointmentRepository.getDoctorUsageReport();
	}
	
	public List<Object[]> getPatientVisitsReport() {
		return appointmentRepository.getPatientVisitsReport();
	}
}
