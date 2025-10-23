package com.campusclinic.service;

import com.campusclinic.model.Doctor;
import com.campusclinic.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
	
	@Autowired
	private final DoctorRepository doctorRepository;
	
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	
	public Doctor create(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAll() {
		return doctorRepository.findAll();
	}
	
	public Optional<Doctor> getById(Long id) {
		return doctorRepository.findById(id);
	}
	
	public List<Doctor> getBySpecialization(String specialization) {
		return doctorRepository.findBySpecialization(specialization);
	}
	
	public Optional<Doctor> update(Long id, Doctor doctor) {
		if (doctorRepository.existsById(id)) {
			doctor.setDoctor_id(id);
			return Optional.of(doctorRepository.save(doctor));
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		if (doctorRepository.existsById(id)) {
			doctorRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
