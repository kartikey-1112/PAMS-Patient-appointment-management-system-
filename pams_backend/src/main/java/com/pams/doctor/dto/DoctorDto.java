package com.pams.doctor.dto;

import com.pams.appointment.model.Appointment;
import com.pams.doctor.model.DoctorTimeSlots;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

public class DoctorDto {

    private int doctorId;

    @NotBlank(message = "Name cannot be blank",groups = {OnRegister.class, OnUpdate.class})
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;


    @NotBlank(message = "Specialization cannot be blank",groups = {OnRegister.class,OnUpdate.class})
    @Size(min = 2, max = 100, message = "Specialization must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "Specialization can only contain letters, spaces, hyphens, or apostrophes")
    private  String specialization;


    @NotBlank(message = "Email cannot be blank",groups = {OnRegister.class, OnLogin.class,OnUpdate.class})
    @Email(message = "Email must be a valid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;


    @NotBlank(message = "Phone number cannot be blank",groups = {OnRegister.class,OnUpdate.class})
    @Pattern(regexp = "^\\+?[0-9\\s()-]{7,20}$", message = "Phone number is not valid")
    @Size(min=10,max = 10, message = "Phone number cannot exceed or less than 10 characters")
    private String phone;


    @NotBlank(message = "Password cannot be blank",groups = {OnRegister.class, OnLogin.class,OnUpdate.class})
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;


    //@NotNull(message = "Doctor time slots list cannot be null")
    private List<DoctorTimeSlots> doctorTimeSlots;


    // @NotNull(message = "Appointments list cannot be null")
    private List<Appointment>  appointments;

    public DoctorDto() {
    }

    public DoctorDto(String name, String specialization, String email, String phone, String password, List<DoctorTimeSlots> doctorTimeSlots, List<Appointment> appointments) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.doctorTimeSlots = doctorTimeSlots;
        this.appointments = appointments;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.password = password;
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


}
