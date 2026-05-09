package com.pams.patient.dto;

import com.pams.appointment.model.Appointment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.util.List;

public class PatientDTO {



    private int patientId;

    @NotBlank(message = "Name is required", groups = {OnRegister.class, OnUpdate.class})
    @Size(min = 2, max = 30, message = "name must be in 2 to 30 characters", groups = {OnRegister.class, OnUpdate.class})
    @Pattern(regexp = "^[A-Za-z ]+$", message = "name can be only letter", groups = {OnRegister.class, OnUpdate.class})
    private String name;

    @NotBlank(message = "Email is required", groups = {OnRegister.class, OnLogin.class, OnUpdate.class})
    @Email(message = "Invalid email format", groups = {OnRegister.class, OnLogin.class, OnUpdate.class})
    private String email;

    @NotBlank(message = "Password is required", groups = {OnRegister.class, OnLogin.class, OnUpdate.class})
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain uppercase, lowercase, digit, and special character",
            groups = {OnRegister.class, OnLogin.class, OnUpdate.class})
    private String password;

    @NotBlank(message = "Phone number is required", groups = {OnRegister.class, OnUpdate.class})
    @Size(min = 10, max = 10, message = "phone number must be of 10 digits", groups = {OnRegister.class, OnUpdate.class})
    @Pattern(regexp = "\\d+", message = "phone number must be only digits", groups = {OnRegister.class, OnUpdate.class})
    private String phone;

    @NotBlank(message = "Address is required", groups = {OnRegister.class, OnUpdate.class})
    @Size(min = 10, max = 50, message = "address must be in 10 to 50 character range", groups = {OnRegister.class, OnUpdate.class})
    private String address;

    @NotNull(message = "Dob must be entered", groups = OnRegister.class)
    @Past(message = "date of birth should only be in past", groups = OnRegister.class)
    private Date dob;

    private List<Appointment> appointments;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
