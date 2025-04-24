package com.example.intro.controllers;

import com.example.intro.models.Employee;
import com.example.intro.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping("/")
    public List<Employee> getEmployees() {
        return employeesRepository.findAll();
    }

}
