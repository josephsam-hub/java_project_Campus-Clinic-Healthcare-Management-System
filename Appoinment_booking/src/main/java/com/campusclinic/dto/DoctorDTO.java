package com.campusclinic.dto;

import java.time.LocalTime;

public class DoctorDTO {
	
	private Long doctor_id;
	private String name;
	private String specialization;
	private String contact_no;
	private LocalTime available_from;
	private LocalTime available_to;
	
	// Default Constructor
	public DoctorDTO() {
	}
	
	// Constructor with all fields
	public DoctorDTO(Long doctor_id, String name, String specialization, 
	                 String contact_no, LocalTime available_from, LocalTime available_to) {
		this.doctor_id = doctor_id;
		this.name = name;
		this.specialization = specialization;
		this.contact_no = contact_no;
		this.available_from = available_from;
		this.available_to = available_to;
	}
	
	// Getters and Setters
	public Long getDoctor_id() {
		return doctor_id;
	}
	
	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getContact_no() {
		return contact_no;
	}
	
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	public LocalTime getAvailable_from() {
		return available_from;
	}
	
	public void setAvailable_from(LocalTime available_from) {
		this.available_from = available_from;
	}
	
	public LocalTime getAvailable_to() {
		return available_to;
	}
	
	public void setAvailable_to(LocalTime available_to) {
		this.available_to = available_to;
	}
}
