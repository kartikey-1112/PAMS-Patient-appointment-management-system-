package com.pams.patient.dto;

import com.pams.patient.model.Patient;

public class PatientMapper {
    public static Patient toEntity(PatientDTO patientDTO)
    {
        Patient p = new Patient();
        p.setName(patientDTO.getName());
        p.setEmail(patientDTO.getEmail());
        p.setPassword(patientDTO.getPassword());
        p.setPhone(patientDTO.getPhone());
        p.setAddress(patientDTO.getAddress());
        p.setDob(patientDTO.getDob());

        return p;

    }
    public static  PatientDTO toDto(Patient patient)
    {
      PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDob(patient.getDob());
        patientDTO.setAppointments(patient.getAppointments());
        return patientDTO;
    }
}
