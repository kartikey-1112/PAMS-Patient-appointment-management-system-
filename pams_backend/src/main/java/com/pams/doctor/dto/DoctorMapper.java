package com.pams.doctor.dto;

import com.pams.doctor.model.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
        doctor.setPassword(doctorDto.getPassword());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setName(doctorDto.getName());
        return doctor;
    }

    public static DoctorDto toDTO(Doctor doctor){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setAppointments(doctor.getAppointments());
        doctorDto.setDoctorTimeSlots(doctor.getDoctorTimeSlots());
        doctorDto.setPhone(doctor.getPhone());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setName(doctor.getName());
        doctorDto.setDoctorId(doctor.getDoctorId());
        doctorDto.setSpecialization(doctor.getSpecialization());

        return doctorDto;
    }

}
