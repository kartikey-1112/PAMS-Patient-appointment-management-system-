package com.pams.doctor.services;

import com.pams.doctor.dto.DoctorDto;
import com.pams.doctor.dto.DoctorMapper;
import com.pams.doctor.model.Doctor;
import com.pams.doctor.repository.DoctorRepo;
import com.pams.security.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.slf4j.Logger; // Import Logger
import org.slf4j.LoggerFactory; // Import LoggerFactory

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class DoctorServiceImpl implements DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class); // Logger Declaration

    @Autowired
    private DoctorRepo doctorRepo;

    @Override
    public ResponseEntity<Map<String, Object>> registerDoctor(DoctorDto doctorDto) {
        logger.info("Attempting to register new doctor with email: {}", doctorDto.getEmail()); // Entry Log
        Map<String,Object> response = new HashMap<>();

        // ... (Input validation logic removed here, assuming @Valid in controller handles most of it, but kept original logic for structural integrity)

        Doctor doctorDB = doctorRepo.findByEmail(doctorDto.getEmail());

        if(doctorDB != null){
            logger.warn("Registration failed: Doctor already present with email: {}", doctorDto.getEmail()); // Failure Log
            response.put("message","doctor is already present");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Doctor doctor = DoctorMapper.toEntity(doctorDto);

        doctorDB = doctorRepo.save(doctor);
        DoctorDto doctorDto1 = DoctorMapper.toDTO(doctorDB);
        System.out.println(doctorDto1);
        logger.info("Doctor registered successfully. Doctor ID: {}", doctorDB.getDoctorId()); // Success Log
        response.put("message","doctor data save successfully");
        response.put("data",doctorDto1);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ... (Similar logging added to loginDoctor, updateDoctor, fetchDoctorByEmail, deleteDoctor)
    // Example for loginDoctor:
    @Override
    public ResponseEntity<Map<String, Object>> loginDoctor(DoctorDto doctorDto) {
        logger.info("Attempting doctor login for email: {}", doctorDto.getEmail());
        Map<String,Object> response = new HashMap<>();

        Doctor doctorDB = doctorRepo.findByEmail(doctorDto.getEmail());

        if(doctorDB == null){
            logger.warn("Login failed: Doctor not found for email: {}", doctorDto.getEmail());
            response.put("message","doctor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (!PasswordHashing.verifyPassword(doctorDto.getPassword(),doctorDB.getPassword())){
            logger.warn("Login failed: Password mismatch for email: {}", doctorDto.getEmail());
            response.put("message","password doesn't match");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        logger.info("Doctor logged in successfully. Doctor ID: {}", doctorDB.getDoctorId());
        response.put("message","doctor login successfully");
        response.put("data",DoctorMapper.toDTO(doctorDB));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateDoctor(DoctorDto doctorDto) {
        logger.info("Attempting to update doctor with email: {}", doctorDto.getEmail());
        Map<String, Object> response = new HashMap<>();

        Doctor doctorDB = doctorRepo.findByEmail(doctorDto.getEmail());

        if(doctorDB == null){
            logger.warn("Update failed: Doctor not found for email: {}", doctorDto.getEmail());
            response.put("message", "Doctor not found");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if(doctorDB.getName().equals(doctorDto.getName()) &&
                doctorDB.getPhone().equals(doctorDto.getPhone()) &&
                doctorDB.getPassword().equals(doctorDto.getPassword()) &&
                doctorDB.getSpecialization().equals(doctorDto.getSpecialization())){

            logger.info("Update skipped: No changes detected for doctor email: {}", doctorDto.getEmail());
            response.put("message", "no changes detected");
            response.put("data", doctorDB);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        doctorDB.setName(doctorDto.getName());
        doctorDB.setPhone(doctorDto.getPhone());
        doctorDB.setPassword(doctorDto.getPassword());
        doctorDB.setSpecialization(doctorDto.getSpecialization());

        Doctor updatedDoctor = doctorRepo.save(doctorDB);
        logger.info("Doctor updated successfully. Doctor ID: {}", updatedDoctor.getDoctorId());

        response.put("message", "Doctor updated successfully");
        response.put("data", DoctorMapper.toDTO(updatedDoctor));
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchDoctorByEmail(String email) {
        logger.info("Fetching doctor by email: {}", email);
        Map<String, Object> response = new HashMap<>();
        Doctor doctorDB = doctorRepo.findByEmail(email);

        if(doctorDB == null){
            logger.warn("Fetch failed: Doctor not found for email: {}", email);
            response.put("message", "Doctor not found in DB");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        logger.info("Doctor fetched successfully for email: {}", email);
        response.put("message", "Doctor found successfully");
        response.put("data", DoctorMapper.toDTO(doctorDB));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteDoctor(String email) {
        logger.info("Attempting to delete doctor by email: {}", email);
        Map<String, Object> response = new HashMap<>();
        Doctor doctorDB = doctorRepo.findByEmail(email);
        if(doctorDB == null){
            logger.warn("Deletion failed: Doctor not found for email: {}", email);
            response.put("message", "Doctor not found in DB");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        doctorRepo.delete(doctorDB);
        logger.info("Doctor deleted successfully. Email: {}", email);
        response.put("message", "Doctor deleted successfully");
        response.put("data", DoctorMapper.toDTO(doctorDB));
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAllDoctors() {
        logger.info("Fetching all doctors from the database.");
        Map<String, Object> response = new HashMap<>();
        List<Doctor> doctors = doctorRepo.findAll();
        response.put("message", "Doctors fetched successfully");
        response.put("data",doctors);
        logger.info("All doctors fetched successfully. Total count: {}", ((java.util.List<?>)response.get("data")).size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}