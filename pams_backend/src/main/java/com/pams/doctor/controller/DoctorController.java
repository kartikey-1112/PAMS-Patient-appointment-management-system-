package com.pams.doctor.controller;

import com.pams.doctor.dto.DoctorDto;
import com.pams.doctor.dto.OnLogin;
import com.pams.doctor.dto.OnRegister;
import com.pams.doctor.dto.OnUpdate;
import com.pams.doctor.model.Doctor;
import com.pams.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Import the @Valid annotation

import java.util.Map;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/registerDoctor")
    public ResponseEntity<Map<String,Object>> registerDoctor(@Valid @RequestBody @Validated(OnRegister.class) DoctorDto doctorDto){
        return doctorService.registerDoctor(doctorDto);
    }

    @PostMapping("/loginDoctor")
    public ResponseEntity<Map<String,Object>> loginDoctor(@RequestBody @Validated(OnLogin.class) DoctorDto doctorDto){
        return doctorService.loginDoctor(doctorDto);
    }

    @PutMapping("/updateDoctor")
    public ResponseEntity<Map<String, Object>> updateDoctor(@RequestBody @Validated(OnUpdate.class) DoctorDto doctorDto){
        return doctorService.updateDoctor(doctorDto);
    }

    @GetMapping("/fetchDoctorByEmail/{email}")
    public ResponseEntity<Map<String, Object>> getDoctor(@PathVariable String email){
        return doctorService.fetchDoctorByEmail(email);
    }

    @DeleteMapping("/deleteDoctor/{email}")
    public ResponseEntity<Map<String, Object>> deleteDoctor(@PathVariable String email){
        return doctorService.deleteDoctor(email);
    }

    @GetMapping("/fetchAllDoctors")
    public ResponseEntity<Map<String,Object>> fetchAllDoctors(){
        return doctorService.fetchAllDoctors();
    }

}