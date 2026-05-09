package com.pams.patient.controller;

import com.pams.patient.dto.*;
import com.pams.patient.model.Patient;
import com.pams.patient.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/loginPatient")
    public ResponseEntity<Map<String, Object>> loginPatient(@RequestBody @Validated(OnLogin.class) PatientDTO patientDTO ) {
        return patientService.loginPatient(patientDTO);
    }

    @PostMapping("/registerPatient")
    public ResponseEntity<Map<String, Object>> registerPatient( @RequestBody @Validated(OnRegister.class) PatientDTO patientDTO) {
        return patientService.registerPatient(patientDTO);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity<?> updatePatientProfile(@RequestBody @Validated(OnUpdate.class) PatientDTO patientDTO)
    {
        return patientService.updatePatientProfile(patientDTO);

    }

    @GetMapping("/fetchPatient/{email}")
    public ResponseEntity<Map<String,Object>> fetchPatient(@PathVariable String email)
    {
        return patientService.fetchPatient(email);
    }


    @DeleteMapping("/deletePatient/{email}")
    public ResponseEntity<Map<String,Object>> deletePatient(@PathVariable String email)
    {
        return patientService.deletePatient(email);
    }

    @GetMapping("/fetchAllPatients")
    public ResponseEntity<Map<String,Object>> fetchAllPatient(){
        return patientService.fetchAllPatient();
    }
}
