package com.pams.doctor.controller;


import com.pams.doctor.model.DoctorTimeSlots;
import com.pams.doctor.services.DoctorTimeSlotsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DoctorTimeSlotsController {

    @Autowired
    DoctorTimeSlotsService doctorTimeSlotsService;

    @GetMapping("/checkTimeSlotsController")
    public String checkTimeSlotsController(){
        return "time slot is ok";
    }

    @PostMapping("/createTimeSlot")
    public ResponseEntity<Map<String,Object>> createTimeSlots(@Valid @RequestBody DoctorTimeSlots doctorTimeSlots){

        Map<String,Object> response = new HashMap<>();

        if (doctorTimeSlots.getDoctor() == null || doctorTimeSlots.getDoctor().getDoctorId() == 0 ||
                doctorTimeSlots.getSlotDate() == null || doctorTimeSlots.getStartTime() == null || doctorTimeSlots.getEndTime() == null) {
            response.put("message", "Please fill all required fields");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return doctorTimeSlotsService.createTimeSlot(doctorTimeSlots);
    }

    @GetMapping("/fetchAllTimeSlots")
    public ResponseEntity<Map<String,Object>> fetchAllTimeSlots(){
        return doctorTimeSlotsService.fetchAllTimeSlots();
    }

    @DeleteMapping("/deleteTimeSlotById/{id}")
    public ResponseEntity<Map<String,Object>> deleteTimeSlotById(@PathVariable int id){
        Map<String,Object> response = new HashMap<>();
        if(id == 0){
            response.put("message", "slot id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return doctorTimeSlotsService.deleteTimeSlotById(id);
    }

}