package com.pams.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pams.appointment.model.Appointment;
import com.pams.security.PasswordHashing;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100)
    private  String specialization;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    @Column(nullable = false,length = 100)
    private String phone;

    @Column(nullable = false,length = 100)

    private String password;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<DoctorTimeSlots>  doctorTimeSlots;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Appointment>  appointments;

    public Doctor() {
    }

    public Doctor(String name, String specialization, String email, String phone, String password) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = PasswordHashing.hashPassword(password);
    }

    public List<DoctorTimeSlots> getDoctorTimeSlots() {
        return doctorTimeSlots;
    }

    public void setDoctorTimeSlots(List<DoctorTimeSlots> doctorTimeSlots) {
        this.doctorTimeSlots = doctorTimeSlots;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", doctorTimeSlots=" + doctorTimeSlots +
                ", appointments=" + appointments +
                '}';
    }
}