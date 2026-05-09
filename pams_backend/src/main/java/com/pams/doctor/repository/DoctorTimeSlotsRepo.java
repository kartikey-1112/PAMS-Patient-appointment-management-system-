package com.pams.doctor.repository;

import com.pams.doctor.model.DoctorTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorTimeSlotsRepo extends JpaRepository<DoctorTimeSlots,Integer> {


}
