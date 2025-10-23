package com.campusclinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class AppointmentCreateRequest {
	
	@NotNull(message = "Doctor ID is required")
	private Long doctor_id;
	
	@NotNull(message = "Appointment date is required")
	private LocalDate appointment_date;
	
	@NotNull(message = "Appointment time is required")
	private LocalTime appointment_time;
	
	private String reason;
	
	// Default Constructor
	public AppointmentCreateRequest() {
	}
	
	// Constructor with all fields
	public AppointmentCreateRequest(Long doctor_id, LocalDate appointment_date, 
	                                LocalTime appointment_time, String reason) {
		this.doctor_id = doctor_id;
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
		this.reason = reason;
	}
	
	// Getters and Setters
	public Long getDoctor_id() {
		return doctor_id;
	}
	
	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public LocalDate getAppointment_date() {
		return appointment_date;
	}
	
	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}
	
	public LocalTime getAppointment_time() {
		return appointment_time;
	}
	
	public void setAppointment_time(LocalTime appointment_time) {
		this.appointment_time = appointment_time;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
}
