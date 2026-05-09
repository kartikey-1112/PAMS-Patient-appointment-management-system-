package com.pams.patient.services;

import com.pams.patient.dto.PatientDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientService {
    ResponseEntity<Map<String, Object>> registerPatient(PatientDTO patientDTO);

    ResponseEntity<Map<String, Object>> loginPatient(PatientDTO patientDTO);

    ResponseEntity<Map<String, Object>> updatePatientProfile(PatientDTO patientDTO);

    ResponseEntity<Map<String, Object>> viewAppointmentHistory();

    ResponseEntity<Map<String, Object>>  fetchPatient(String patientEmail);

    ResponseEntity<Map<String, Object>>  deletePatient(String patientEmail);

    ResponseEntity<Map<String, Object>>  fetchAllPatient();

}
