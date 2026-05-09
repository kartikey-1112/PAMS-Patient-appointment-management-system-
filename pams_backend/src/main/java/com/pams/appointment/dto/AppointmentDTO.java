package com.pams.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pams.doctor.model.Doctor;
import com.pams.doctor.model.DoctorTimeSlots;
import com.pams.patient.model.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentDTO {

    private int appointmentId;

    @NotNull(message = "Patient information must be provided")
    @JsonIgnoreProperties("appointments")
    private Patient patient;

    @NotNull(message = "Doctor information must be provided")
    @JsonIgnoreProperties({"doctorTimeSlots","appointments"})
    private Doctor doctor;

    @NotNull(message = "Doctor time slot must be provided")
    @JsonIgnoreProperties({"appointment","doctor"})
    private DoctorTimeSlots doctorTimeSlots;

    @NotBlank(message = "Appointment status must not be blank")
    private String status = "BOOKED";

    public AppointmentDTO() {}

    public AppointmentDTO(int appointmentId, Patient patient, Doctor doctor, DoctorTimeSlots doctorTimeSlots, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.doctorTimeSlots = doctorTimeSlots;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorTimeSlots getDoctorTimeSlots() {
        return doctorTimeSlots;
    }

    public void setDoctorTimeSlots(DoctorTimeSlots doctorTimeSlots) {
        this.doctorTimeSlots = doctorTimeSlots;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
