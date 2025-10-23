package com.campusclinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
	
	private Long appointment_id;
	private Long user_id;
	private String user_name;
	private Long doctor_id;
	private String doctor_name;
	private String doctor_specialization;
	private LocalDate appointment_date;
	private LocalTime appointment_time;
	private String status;
	private String reason;
	
	// Default Constructor
	public AppointmentDTO() {
	}
	
	// Constructor with all fields
	public AppointmentDTO(Long appointment_id, Long user_id, String user_name, 
	                      Long doctor_id, String doctor_name, String doctor_specialization,
	                      LocalDate appointment_date, LocalTime appointment_time, 
	                      String status, String reason) {
		this.appointment_id = appointment_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.doctor_specialization = doctor_specialization;
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
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public Long getDoctor_id() {
		return doctor_id;
	}
	
	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public String getDoctor_name() {
		return doctor_name;
	}
	
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	
	public String getDoctor_specialization() {
		return doctor_specialization;
	}
	
	public void setDoctor_specialization(String doctor_specialization) {
		this.doctor_specialization = doctor_specialization;
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
