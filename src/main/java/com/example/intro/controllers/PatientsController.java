package com.example.intro.controllers;

import com.example.intro.models.Department;
import com.example.intro.models.Employee;
import com.example.intro.models.Patient;
import com.example.intro.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPacients() {
        return patientsRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getEmployeeById(@PathVariable("id") Long patientId) {

        return patientsRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @GetMapping("/byDateBirthRange")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDateBirthRange(
            @RequestParam(name = "startDate", required = true) String startDate,
            @RequestParam(name = "endDate", required = true) String endDate) {

        java.util.Date utilDateStart = null;
        java.util.Date utilDateEnd = null;
        java.sql.Date sqlDateStart = null;
        java.sql.Date sqlDateEnd = null;
        try {
            utilDateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error parsing start date");
        }

        try {
            utilDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error parsing end date");
        }
        sqlDateStart = new java.sql.Date(utilDateStart.getTime());
        sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());

        return patientsRepository.findAllByDateOfBirthBetween(sqlDateStart, sqlDateEnd);

    }

    @GetMapping("/byDepartment")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDepartment(
            @RequestParam(name = "department", required = true) Optional<String> departmentOptional) {

        if (departmentOptional.isPresent()) {
            return patientsRepository.findAllByEmployeesDepartment(Department.valueOf(departmentOptional.get().toUpperCase()));
        }else {
            return null;
        }

    }
}
