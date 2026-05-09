package com.pams.patient.services.serviceImpl;

import com.pams.patient.dto.PatientDTO;
import com.pams.patient.dto.PatientMapper;
import com.pams.patient.model.Patient;
import com.pams.patient.repository.PatientRepo;
import com.pams.patient.services.PatientService;
import com.pams.security.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepo patientRepo;


    @Override
    public ResponseEntity<Map<String, Object>> registerPatient(PatientDTO patient) {

        Map<String, Object> map = new HashMap<String, Object>();

        logger.info("Attempting to register new patient with email: {}", patient.getEmail());

        Patient patientDetail = patientRepo.findByEmail(patient.getEmail());

        if (patientDetail != null) {
            logger.warn("Patient registration failed. Patient already exists with email: {}", patient.getEmail());
            map.put("message", "Patient Already Exist");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);

        }

        Patient patientData = patientRepo.save(PatientMapper.toEntity(patient));
        logger.info("Successfully registered patient with ID: {} and email: {}", patientData.getPatientId(), patientData.getEmail());
        map.put("message", "Patient Registered Successfully");
        map.put("status", true);
        map.put("data",  PatientMapper.toDto(patientData));


        return new ResponseEntity<>(map, HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<Map<String, Object>> loginPatient(PatientDTO patient) {

        logger.info("Attempting to log in patient with email: {}", patient.getEmail());
        Map<String, Object> map = new HashMap<String, Object>();

        Patient patientData = patientRepo.findByEmail(patient.getEmail());

        if (patientData == null) {
            logger.warn("Login failed for email: {}. Invalid email/patient not found.", patient.getEmail());
            map.put("message", "Invalid Email");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
        if (!PasswordHashing.verifyPassword(patient.getPassword(),patientData.getPassword())) {
            logger.warn("Login failed for email: {}. Invalid password.", patient.getEmail());
            map.put("message", "Invalid Password");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }
        logger.info("Patient successfully logged in: {}", patient.getEmail());
        map.put("message", "Patient Logged In");
        map.put("status", true);
        map.put("data",PatientMapper.toDto(patientData));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updatePatientProfile(PatientDTO newPatient) {

        logger.info("Attempting to update profile for patient with email: {}", newPatient.getEmail());
        Map<String, Object> map = new HashMap<String, Object>();

        Patient existingPatient = patientRepo.findByEmail(newPatient.getEmail());

        if(existingPatient!=null)
        {
            logger.debug("Existing patient data before update: {}", existingPatient);
            existingPatient.setName(newPatient.getName());
            existingPatient.setAddress(newPatient.getAddress());
            existingPatient.setPhone(newPatient.getPhone());
            existingPatient.setEmail(newPatient.getEmail());
            existingPatient.setPassword(newPatient.getPassword());

            patientRepo.save(existingPatient);

            logger.info("Patient profile updated successfully for email: {}", newPatient.getEmail());
            map.put("message", "Patient Updated");
            map.put("status", true);
            map.put("data",PatientMapper.toDto(existingPatient));

            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }

        logger.warn("Profile update failed. Patient not found with email: {}", newPatient.getEmail());
        map.put("message", "Patient do not exist");
        map.put("status", false);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, Object>> viewAppointmentHistory() {
        return  null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchPatient(String patientEmail) {

        logger.info("Attempting to fetch patient by email: {}", patientEmail);
        Map<String,Object> map = new HashMap<>();

        Patient patientData = patientRepo.findByEmail(patientEmail);

        if(patientData==null)
        {
            logger.warn("Patient fetch failed. Patient not found with email: {}", patientEmail);
            map.put("message", "Patient do not exist");
            map.put("status", false);
            map.put("data",patientData);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        logger.info("Patient successfully fetched: {}", patientEmail);
        map.put("message", "Patient fetched");
        map.put("status", true);
        map.put("data",patientData);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Map<String, Object>> deletePatient(String patientEmail) {
        logger.info("Attempting to delete patient by email: {}", patientEmail);
        Map<String,Object> map = new HashMap<>();

        Patient patientData = patientRepo.findByEmail(patientEmail);

        if(patientData==null)
        {
            logger.warn("Patient deletion failed. Patient not found with email: {}", patientEmail);
            map.put("message", "Patient do not exist");
            map.put("status", false);
            map.put("data",patientData);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        patientRepo.deleteById(patientData.getPatientId());

        logger.info("Patient successfully deleted with ID: {} and email: {}", patientData.getPatientId(), patientEmail);
        map.put("message", "Patient deleted ");
        map.put("status", true);
        map.put("data",patientData);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAllPatient() {

        Map<String,Object> map = new HashMap<>();
        List<Patient> patientData = patientRepo.findAll();
        if(patientData.isEmpty()){
            map.put("message", "Patient not found");
            map.put("status", false);
            map.put("data",patientData);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }

        map.put("message", "Patient found");
        map.put("status", true);
        map.put("data",patientData);
        return ResponseEntity.status(HttpStatus.OK).body(map);

    }


}
