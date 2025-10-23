package com.campusclinic.repository;

import com.campusclinic.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	// Find appointments by user ID
	@Query("SELECT a FROM Appointment a WHERE a.user.user_id = :userId")
	List<Appointment> findByUserId(@Param("userId") Long userId);
	
	// Find appointments by doctor ID
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctor_id = :doctorId")
	List<Appointment> findByDoctorId(@Param("doctorId") Long doctorId);
	
	// Find appointments by status
	List<Appointment> findByStatus(String status);
	
	// Find appointments by date
	@Query("SELECT a FROM Appointment a WHERE a.appointment_date = :date")
	List<Appointment> findByAppointmentDate(@Param("date") LocalDate date);
	
	// Find appointments by user ID and status
	@Query("SELECT a FROM Appointment a WHERE a.user.user_id = :userId AND a.status = :status")
	List<Appointment> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
	
	// Find appointments by doctor ID and status
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctor_id = :doctorId AND a.status = :status")
	List<Appointment> findByDoctorIdAndStatus(@Param("doctorId") Long doctorId, @Param("status") String status);
	
	// Find appointments by doctor ID and date
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctor_id = :doctorId AND a.appointment_date = :date")
	List<Appointment> findByDoctorIdAndAppointmentDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);
	
	// Find appointments by doctor ID, date, and time (for double booking check)
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctor_id = :doctorId " +
	       "AND a.appointment_date = :date AND a.appointment_time = :time")
	List<Appointment> findByDoctorIdAndAppointmentDateAndTime(@Param("doctorId") Long doctorId, 
	                                                           @Param("date") LocalDate date, 
	                                                           @Param("time") LocalTime time);
	
	// Find appointments by date range
	@Query("SELECT a FROM Appointment a WHERE a.appointment_date BETWEEN :startDate AND :endDate")
	List<Appointment> findByDateRange(@Param("startDate") LocalDate startDate, 
	                                  @Param("endDate") LocalDate endDate);
	
	// Find upcoming appointments for a user
	@Query("SELECT a FROM Appointment a WHERE a.user.user_id = :userId " +
	       "AND a.appointment_date >= :currentDate " +
	       "AND a.status = 'Scheduled' " +
	       "ORDER BY a.appointment_date ASC, a.appointment_time ASC")
	List<Appointment> findUpcomingAppointmentsByUser(@Param("userId") Long userId, 
	                                                  @Param("currentDate") LocalDate currentDate);
	
	// Find upcoming appointments for a doctor
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctor_id = :doctorId " +
	       "AND a.appointment_date >= :currentDate " +
	       "AND a.status = 'Scheduled' " +
	       "ORDER BY a.appointment_date ASC, a.appointment_time ASC")
	List<Appointment> findUpcomingAppointmentsByDoctor(@Param("doctorId") Long doctorId, 
	                                                    @Param("currentDate") LocalDate currentDate);
	
	// Count appointments by status
	Long countByStatus(String status);
	
	// Count appointments by doctor ID
	@Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.doctor_id = :doctorId")
	Long countByDoctorId(@Param("doctorId") Long doctorId);
	
	// Count appointments by user ID
	@Query("SELECT COUNT(a) FROM Appointment a WHERE a.user.user_id = :userId")
	Long countByUserId(@Param("userId") Long userId);
	
	// ==================== Report Queries ====================
	
	// Doctor utilization report
	@Query("SELECT d.name, d.specialization, COUNT(a.appointment_id) as total_appointments, " +
	       "SUM(CASE WHEN a.status = 'Completed' THEN 1 ELSE 0 END) as completed_appointments, " +
	       "SUM(CASE WHEN a.status = 'Cancelled' THEN 1 ELSE 0 END) as cancelled_appointments " +
	       "FROM Doctor d LEFT JOIN Appointment a ON d.doctor_id = a.doctor.doctor_id " +
	       "GROUP BY d.doctor_id, d.name, d.specialization " +
	       "ORDER BY total_appointments DESC")
	List<Object[]> getDoctorUsageReport();
	
	// Patient visit history report
	@Query("SELECT u.name, u.email, COUNT(a.appointment_id) as total_visits, " +
	       "MAX(a.appointment_date) as last_visit_date " +
	       "FROM User u LEFT JOIN Appointment a ON u.user_id = a.user.user_id " +
	       "WHERE u.role = 'USER' " +
	       "GROUP BY u.user_id, u.name, u.email " +
	       "ORDER BY total_visits DESC")
	List<Object[]> getPatientVisitsReport();
	
	// Appointments by status report
	@Query("SELECT a.status, COUNT(a.appointment_id) as count " +
	       "FROM Appointment a " +
	       "GROUP BY a.status")
	List<Object[]> getAppointmentsByStatusReport();
	
	// Daily appointments report
	@Query("SELECT a.appointment_date, COUNT(a.appointment_id) as count " +
	       "FROM Appointment a " +
	       "WHERE a.appointment_date BETWEEN :startDate AND :endDate " +
	       "GROUP BY a.appointment_date " +
	       "ORDER BY a.appointment_date ASC")
	List<Object[]> getDailyAppointmentsReport(@Param("startDate") LocalDate startDate, 
	                                          @Param("endDate") LocalDate endDate);
	
	// Monthly appointments by specialization
	@Query("SELECT d.specialization, COUNT(a.appointment_id) as count " +
	       "FROM Appointment a JOIN a.doctor d " +
	       "WHERE a.appointment_date BETWEEN :startDate AND :endDate " +
	       "GROUP BY d.specialization " +
	       "ORDER BY count DESC")
	List<Appointment> getAppointmentsBySpecialization(@Param("startDate") LocalDate startDate, 
	                                                @Param("endDate") LocalDate endDate);
}
