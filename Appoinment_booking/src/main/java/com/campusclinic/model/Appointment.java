package com.campusclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointment_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull(message = "User is required")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull(message = "Doctor is required")
	private Doctor doctor;
	
	@NotNull(message = "Appointment date is required")
	@Column(nullable = false)
	private LocalDate appointment_date;
	
	@NotNull(message = "Appointment time is required")
	@Column(nullable = false)
	private LocalTime appointment_time;
	
	@NotBlank(message = "Status is required")
	@Column(nullable = false, length = 20)
	private String status; // "Scheduled", "Completed", "Cancelled"
	
	@Column(columnDefinition = "TEXT")
	private String reason;
	
	// Default Constructor
	public Appointment() {
	}
	
	// Constructor without ID
	public Appointment(User user, Doctor doctor, LocalDate appointment_date, 
	                   LocalTime appointment_time, String status, String reason) {
		this.user = user;
		this.doctor = doctor;
		this.appointment_date = appointment_date;
		this.appointment_time = appointment_time;
		this.status = status;
		this.reason = reason;
	}
	
	// Getters and Setters
	public Long getAppointment_id() {
		return appointment_id;
	}
	
	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
}
