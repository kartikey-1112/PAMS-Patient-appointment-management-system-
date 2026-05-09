package com.pams.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pams.doctor.model.Doctor;
import com.pams.doctor.model.DoctorTimeSlots;
import com.pams.patient.model.Patient;
import jakarta.persistence.*;


@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    @JsonIgnoreProperties("appointments")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId",nullable = false)
    @JsonIgnoreProperties({"doctorTimeSlots","appointments"})
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "timeSlotId", nullable = false)
    @JsonIgnoreProperties({"appointment","doctor"})
    private DoctorTimeSlots doctorTimeSlots;

    @Column(nullable = false,length = 100)
    private String status = "BOOKED";

    public Appointment(Patient patient, Doctor doctor, DoctorTimeSlots doctorTimeSlots, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.doctorTimeSlots = doctorTimeSlots;
        this.status = status;
    }

    public Appointment() {}

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