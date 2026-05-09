package com.pams.appointment.services;



import com.pams.appointment.dto.AppointmentDTO;
import com.pams.appointment.model.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppointmentService {
    ResponseEntity<Map<String, Object>> createAppointment(AppointmentDTO appointmentDTO);
    ResponseEntity<Map<String, Object>> updateAppointment(Appointment appointment);
    ResponseEntity<Map<String, Object>> deleteAppointment(int id);
    ResponseEntity<Map<String, Object>> fetchAppointmentById(int id);
    ResponseEntity<Map<String, Object>> fetchAllAppointments();
    ResponseEntity<Map<String, Object>> cancleAppointment(int appointmentId);
}