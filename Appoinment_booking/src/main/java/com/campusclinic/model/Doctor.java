package com.campusclinic.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "doctors")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctor_id;
	
	@NotBlank(message = "Doctor name is required")
	@Size(max = 50, message = "Name cannot exceed 50 characters")
	@Column(nullable = false, length = 50)
	private String name;
	
	@NotBlank(message = "Specialization is required")
	@Size(max = 50, message = "Specialization cannot exceed 50 characters")
	@Column(nullable = false, length = 50)
	private String specialization;
	
	@Size(max = 20, message = "Contact number cannot exceed 20 characters")
	@Column(length = 20)
	private String contact_no;
	
	@NotNull(message = "Available from time is required")
	@Column(nullable = false)
	private LocalTime available_from;
	
	@NotNull(message = "Available to time is required")
	@Column(nullable = false)
	private LocalTime available_to;
	
	// Default Constructor
	public Doctor() {
	}
	
	// Constructor without ID
	public Doctor(String name, String specialization, String contact_no, 
	              LocalTime available_from, LocalTime available_to) {
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
