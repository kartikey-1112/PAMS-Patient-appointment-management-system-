package com.pams.doctor.services;

import com.pams.doctor.model.Doctor;
import com.pams.doctor.model.DoctorTimeSlots;
import com.pams.doctor.repository.DoctorRepo;
import com.pams.doctor.repository.DoctorTimeSlotsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger; // Import Logger
import org.slf4j.LoggerFactory; // Import LoggerFactory

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorTimeSlotsServiceImpl implements DoctorTimeSlotsService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorTimeSlotsServiceImpl.class); // Logger Declaration

    @Autowired
    DoctorTimeSlotsRepo doctorTimeSlotsRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Override
    public ResponseEntity<Map<String, Object>> createTimeSlot(DoctorTimeSlots doctorTimeSlots) {
        int doctorId = doctorTimeSlots.getDoctor() != null ? doctorTimeSlots.getDoctor().getDoctorId() : 0;
        logger.info("Creating time slot for Doctor ID: {} on date: {}", doctorId, doctorTimeSlots.getSlotDate()); // Entry Log
        Map<String, Object> response = new HashMap<>();

        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);

        if(doctor == null){
            logger.warn("Time slot creation failed: Doctor not found with ID: {}", doctorId); // Failure Log
            response.put("message", "Doctor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        doctorTimeSlots.setDoctor(doctor);

        DoctorTimeSlots doctorTimeSlotsDB = doctorTimeSlotsRepo.save(doctorTimeSlots);
        logger.info("Time slot created successfully. Slot ID: {}", doctorTimeSlotsDB.getTimeSlotId()); // Success Log
        response.put("message", "Doctor time slots created successfully");
        response.put("data", doctorTimeSlotsDB);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // ... (Similar logging added to deleteTimeSlotById, fetchAllTimeSlots)

    @Override
    public ResponseEntity<Map<String, Object>> updateTimeSlot(DoctorTimeSlots doctorTimeSlots) {
        // Implementation for updateTimeSlot (currently returning null) should also include logging.
        logger.warn("Update Time Slot method not yet implemented.");
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTimeSlotById(int id) {
        logger.info("Attempting to delete time slot with ID: {}", id);
        Map<String, Object> response = new HashMap<>();

        DoctorTimeSlots doctorTimeSlotsDB = doctorTimeSlotsRepo.findById(id).orElse(null);
        if(doctorTimeSlotsDB == null){
            logger.warn("Deletion failed: Time slot not found with ID: {}", id);
            response.put("message", "Doctor time slots not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        doctorTimeSlotsRepo.delete(doctorTimeSlotsDB);
        logger.info("Time slot deleted successfully. Slot ID: {}", id);
        response.put("message", "Doctor time slots deleted successfully");
        response.put("data", doctorTimeSlotsDB);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAllTimeSlots() {
        logger.info("Fetching all doctor time slots.");
        Map<String, Object> response = new HashMap<>();

        List<DoctorTimeSlots> doctorTimeSlots = doctorTimeSlotsRepo.findAll();
        if(doctorTimeSlots.isEmpty()){
            logger.info("No doctor time slots found in DB.");
            response.put("message", "Doctor time slots not found");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        logger.info("Fetched {} time slots successfully.", doctorTimeSlots.size());
        response.put("message", "Doctor time slots fetch from DB successfully");
        response.put("data", doctorTimeSlots);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}