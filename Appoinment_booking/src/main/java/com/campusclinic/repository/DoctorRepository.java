package com.campusclinic.repository;

import com.campusclinic.model.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	// Find doctors by specialization
	List<Doctor> findBySpecialization(String specialization);
	
	// Find doctors by name (search functionality)
	List<Doctor> findByNameContainingIgnoreCase(String name);
	
	// Find all doctors ordered by name
	List<Doctor> findAllByOrderByNameAsc();
	
	// Custom query to find doctors with appointments count
	@Query("SELECT d.doctor_id, d.name, d.specialization, COUNT(a.appointment_id) " +
	       "FROM Doctor d LEFT JOIN Appointment a ON d.doctor_id = a.doctor.doctor_id " +
	       "GROUP BY d.doctor_id, d.name, d.specialization " +
	       "ORDER BY COUNT(a.appointment_id) DESC")
	List<Object[]> findDoctorsWithAppointmentCount();
}
