package com.example.intro.repositories;

import com.example.intro.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByDateOfBirthBetween(Date start, Date end);
}
