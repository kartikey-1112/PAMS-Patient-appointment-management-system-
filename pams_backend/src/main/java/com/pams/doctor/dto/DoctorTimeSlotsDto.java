package com.pams.doctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pams.doctor.model.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;

public class DoctorTimeSlotsDto {

    private Integer timeSlotId;

    @NotNull(message = "Doctor must be specified")
    private Doctor doctor;

    @NotNull(message = "Slot date must be provided")
    private Date slotDate;

    @NotNull(message = "Start time must be specified")
    private Time startTime;

    @NotNull(message = "End time must be specified")
    private Time endTime;


    public DoctorTimeSlotsDto() {
    }

    public DoctorTimeSlotsDto(Doctor doctor, Date slotDate, Time startTime, Time endTime) {
        this.doctor = doctor;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
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
}

