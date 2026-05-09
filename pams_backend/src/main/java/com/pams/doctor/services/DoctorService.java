package com.pams.doctor.services;

import com.pams.doctor.dto.DoctorDto;
import com.pams.doctor.model.Doctor;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface DoctorService {

public ResponseEntity<Map<String,Object>> registerDoctor(DoctorDto doctorDto);

public ResponseEntity<Map<String,Object>> loginDoctor(DoctorDto doctorDto);


public ResponseEntity<Map<String,Object>> updateDoctor(DoctorDto doctorDto);


// delete doctor
public ResponseEntity<Map<String,Object>> fetchDoctorByEmail(String email);

    public ResponseEntity<Map<String,Object>> deleteDoctor(String email);

    public ResponseEntity<Map<String,Object>> fetchAllDoctors();

}
