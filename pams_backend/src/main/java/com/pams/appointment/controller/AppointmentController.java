package com.pams.appointment.controller;

import com.pams.appointment.dto.AppointmentDTO;
import com.pams.appointment.model.Appointment;
import com.pams.appointment.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/testAppointmentController")
    public String testAppointmentController(){
        return "appointment controller are ready to run ";
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<Map<String,Object>> createAppointment(@Valid  @RequestBody AppointmentDTO appointmentDTO){
        return appointmentService.createAppointment(appointmentDTO);
    }

    @PostMapping("/updateAppointment")
    public ResponseEntity<Map<String,Object>> updateAppointment(@RequestBody  AppointmentDTO appointmentDTO){
        return null;
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<Map<String,Object>> deleteAppointment(@PathVariable int id){
        return appointmentService.deleteAppointment(id);
    }

    @GetMapping("/fetchAppointmentById/{id}")
    public ResponseEntity<Map<String,Object>> fetchAppointmentById(@PathVariable int id){
        return appointmentService.fetchAppointmentById(id);
    }

    @GetMapping("/fetchAllAppointment")
    public ResponseEntity<Map<String,Object>> fetchAllAppointment(){
        return appointmentService.fetchAllAppointments();
    }

    @PutMapping("/cancleAppointment/{appointmentId}")
    public ResponseEntity<Map<String,Object>> cancelAppointment(@PathVariable int appointmentId){
        return appointmentService.cancleAppointment(appointmentId);
    }
}