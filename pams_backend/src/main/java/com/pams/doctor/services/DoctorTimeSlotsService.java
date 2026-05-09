package com.pams.doctor.services;

import com.pams.doctor.model.DoctorTimeSlots;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DoctorTimeSlotsService {
    ResponseEntity<Map<String,Object>> createTimeSlot(DoctorTimeSlots doctorTimeSlots);
    ResponseEntity<Map<String,Object>> updateTimeSlot(DoctorTimeSlots doctorTimeSlots);
    ResponseEntity<Map<String,Object>> deleteTimeSlotById(int id);
    ResponseEntity<Map<String,Object>> fetchAllTimeSlots();
}