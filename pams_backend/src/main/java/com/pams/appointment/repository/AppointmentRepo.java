package com.pams.appointment.repository;


import com.pams.appointment.model.Appointment;
import com.pams.doctor.model.DoctorTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    Appointment findByDoctorTimeSlots(DoctorTimeSlots doctorTimeSlots);
}