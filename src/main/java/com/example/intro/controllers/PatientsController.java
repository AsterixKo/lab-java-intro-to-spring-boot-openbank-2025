package com.example.intro.controllers;

import com.example.intro.models.Patient;
import com.example.intro.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
