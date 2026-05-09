package com.pams.doctor.repository;

import com.pams.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
    public Doctor findByEmail(String email);
}
