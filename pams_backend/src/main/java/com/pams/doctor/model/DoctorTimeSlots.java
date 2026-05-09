package com.pams.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "doctorTimeSlots")
public class DoctorTimeSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int timeSlotId;

    @ManyToOne
    @JoinColumn(name = "doctorId",nullable = false)
    @JsonIgnoreProperties({"doctorTimeSlots", "appointments", "password"})
    private Doctor doctor;

    @Column(nullable = false)
    private Date slotDate ;

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Time endTime;


    //we cant delete appointment beacuse of time coupling @OneToOne Relationship
//    @OneToOne(mappedBy = "doctorTimeSlots",cascade = CascadeType.PERSIST)
//    @JsonIgnoreProperties("doctorTimeSlots")
//    private Appointment appointment;
//
//    public Appointment getAppointment() {
//        return appointment;
//    }
//
//    public void setAppointment(Appointment appointment) {
//        this.appointment = appointment;
//    }

    public DoctorTimeSlots() {
    }

    public DoctorTimeSlots(Doctor doctor, Date slotDate, Time startTime, Time endTime) {
        this.doctor = doctor;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "DoctorTimeSlots{" +
                "timeSlotId=" + timeSlotId +
                ", doctor=" + doctor +
                ", slotDate=" + slotDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}