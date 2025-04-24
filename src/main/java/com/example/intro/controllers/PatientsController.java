package com.example.intro.controllers;

import com.example.intro.models.Employee;
import com.example.intro.models.Patient;
import com.example.intro.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPacients(){
        return patientsRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getEmployeeById(@PathVariable("id") Long patientId) {

        return patientsRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }
}
