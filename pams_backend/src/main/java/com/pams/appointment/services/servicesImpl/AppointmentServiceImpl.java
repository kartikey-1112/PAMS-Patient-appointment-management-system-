package com.pams.appointment.services.servicesImpl;

import com.pams.appointment.dto.AppointmentDTO;
import com.pams.appointment.dto.AppointmentMapper;
import com.pams.appointment.model.Appointment;
import com.pams.appointment.repository.AppointmentRepo;
import com.pams.appointment.services.AppointmentService;
import com.pams.doctor.model.Doctor;
import com.pams.doctor.model.DoctorTimeSlots;
import com.pams.doctor.repository.DoctorRepo;
import com.pams.doctor.repository.DoctorTimeSlotsRepo;
import com.pams.patient.model.Patient;
import com.pams.patient.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AppointmentServiceImpl  implements AppointmentService {

    // Initialize Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorTimeSlotsRepo doctorTimeSlotsRepo;

    @Override
    public ResponseEntity<Map<String, Object>> createAppointment(AppointmentDTO appointmentDTO) {
        logger.info("Starting appointment creation process for Doctor ID: {} and Patient ID: {}",
                appointmentDTO.getDoctor() != null ? appointmentDTO.getDoctor().getDoctorId() : "N/A",
                appointmentDTO.getPatient() != null ? appointmentDTO.getPatient().getPatientId() : "N/A");

        Map<String, Object> response = new HashMap<>();

        // Doctor Check
        Doctor doctor = doctorRepo.findById(appointmentDTO.getDoctor().getDoctorId()).orElse(null);
        if (doctor == null) {
            logger.warn("Doctor Not Found with ID: {}. Throwing exception to be handled globally.", appointmentDTO.getDoctor().getDoctorId());
            response.put("message", "Doctor Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Patient Check
        Patient patient =  patientRepo.findById(appointmentDTO.getPatient().getPatientId()).orElse(null);
        if (patient == null) {
            logger.warn("Patient Not Found with ID: {}.", appointmentDTO.getPatient().getPatientId());
            response.put("message", "Patient Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Time Slot Check
        DoctorTimeSlots doctorTimeSlots = doctorTimeSlotsRepo.findById(appointmentDTO.getDoctorTimeSlots().getTimeSlotId()).orElse(null);
        if (doctorTimeSlots == null) {
            logger.warn("Time Slot Not Found with ID: {}.", appointmentDTO.getDoctorTimeSlots().getTimeSlotId());
            response.put("message", "Time Slot Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Appointment Conflict Check
        Appointment checkAppointment = appointmentRepo.findByDoctorTimeSlots(doctorTimeSlots);
        if (checkAppointment != null) {
            logger.warn("Appointment already booked for Time Slot ID: {}. Conflict detected.", appointmentDTO.getDoctorTimeSlots().getTimeSlotId());
            response.put("message", "Appointment already booked");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // Save Appointment
        Appointment appointment = AppointmentMapper.toEntity(appointmentDTO,patient,doctor,doctorTimeSlots);

        Appointment appointmentDB = appointmentRepo.save(appointment);

        logger.info("Appointment successfully created with ID: {}", appointmentDB.getAppointmentId());

        AppointmentDTO appointmentDTOResponse = AppointmentMapper.toDTO(appointmentDB);
        response.put("data", appointmentDTOResponse); // Returning entity as per original code
        response.put("message", "Appointment Created");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateAppointment(Appointment appointment) {
        logger.warn("Update Appointment method is not yet implemented (returns null).");
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteAppointment(int id) {
        logger.info("Attempting to delete appointment with ID: {}", id);
        Map<String, Object> response = new HashMap<>();

        if(id == 0){
            logger.warn("Invalid ID (0) provided for deleteAppointment. Returning BAD_REQUEST.");
            response.put("message", "id not found");
            response.put("data", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Appointment appointmentDB = appointmentRepo.findById(id).orElse(null);

        if (appointmentDB == null) {
            logger.warn("Appointment Not Found with ID: {} for deletion. Returning NOT_FOUND.", id);
            response.put("message", "Appointment Not Found");
            response.put("data", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        appointmentRepo.delete(appointmentDB);
        logger.info("Appointment successfully deleted with ID: {}", id);

        AppointmentDTO appointmentDTO = AppointmentMapper.toDTO(appointmentDB);

        response.put("message", "Appointment Deleted Successfully");
        response.put("data", appointmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAppointmentById(int id) {
        logger.info("Attempting to fetch appointment with ID: {}", id);
        Map<String, Object> response = new HashMap<>();

        if(id == 0){
            logger.warn("Invalid ID (0) provided for fetchAppointmentById. Returning BAD_REQUEST.");
            response.put("message", "appointment is not found");
            response.put("data", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Appointment appointments = appointmentRepo.findById(id).orElse(null);

        if (appointments == null) {
            logger.warn("Appointment Not Found with ID: {} for fetching. Returning NOT_FOUND.", id);
            response.put("message", "Appointment Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        AppointmentDTO appointmentDTO = AppointmentMapper.toDTO(appointments);
        logger.info("Appointment successfully fetched with ID: {}", id);

        response.put("message", "appointment fetch from DB successfully");
        response.put("data", appointmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAllAppointments(){
        logger.info("Attempting to fetch all appointments.");
        Map<String, Object> response = new HashMap<>();
        List<Appointment> appointments = appointmentRepo.findAll();

        if(appointments.isEmpty()){
            logger.info("No appointments found in the database. Returning NOT_FOUND.");
            response.put("message", "No appointments found");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        logger.info("Successfully fetched {} appointments.", appointments.size());
        response.put("message", "All appointment fetch from DB successfully");
        response.put("data", appointments);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> cancleAppointment(int appointmentId) {
        logger.info("Attempting to cancle appointment with ID: {}", appointmentId);
        Map<String, Object> response = new HashMap<>();
        if(appointmentId == 0){
            logger.warn("Invalid ID (0) provided for cancleAppointment. Returning BAD_REQUEST.");
            response.put("message", "appointment is not found");
            response.put("data", appointmentId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Appointment appointmentDB = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointmentDB == null) {
            logger.warn("Appointment Not Found with ID: {}. Returning NOT_FOUND.", appointmentId);
            response.put("message", "Appointment Not Found");
            response.put("data", appointmentId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        appointmentDB.setStatus("CANCLED");
        Appointment appointment = appointmentRepo.save(appointmentDB);

        logger.info("Appointment successfully cancled with ID: {}", appointmentId);
        AppointmentDTO appointmentDTO = AppointmentMapper.toDTO(appointment);
        
        response.put("message", "appointment cancled from DB successfully");
        response.put("data", appointmentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}