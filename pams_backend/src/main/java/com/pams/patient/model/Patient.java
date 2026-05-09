package com.pams.patient.model;

import com.pams.security.PasswordHashing;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import com.pams.appointment.model.Appointment;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    @Column(nullable = false,length = 10)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(nullable = false,length = 100)
    @Past
    private Date dob;

    @Column(nullable = false,length = 100)
    private String password;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Patient() {
    }

    public Patient(String name, String email, String phone, String address, Date dob, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.password = password;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordHashing.hashPassword(password);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}