package com.pams.appointment.dto;

import com.pams.appointment.model.Appointment;
import com.pams.doctor.model.Doctor;
import com.pams.doctor.model.DoctorTimeSlots;
import com.pams.patient.model.Patient;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentDTO appointmentDTO, Patient patient, Doctor doctor, DoctorTimeSlots doctorTimeSlots){
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDoctorTimeSlots(doctorTimeSlots);
        appointment.setStatus(appointmentDTO.getStatus());
        return appointment;
    }

    public static AppointmentDTO toDTO(Appointment appointment){
        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setPatient(appointment.getPatient());
        dto.setDoctor(appointment.getDoctor());
        dto.setDoctorTimeSlots(appointment.getDoctorTimeSlots());
        dto.setStatus(appointment.getStatus());
        return dto;
    }

}
