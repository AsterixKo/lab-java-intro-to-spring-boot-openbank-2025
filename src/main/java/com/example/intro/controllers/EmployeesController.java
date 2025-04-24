package com.example.intro.controllers;

import com.example.intro.models.Department;
import com.example.intro.models.Employee;
import com.example.intro.models.Status;
import com.example.intro.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees() {
        return employeesRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable("id") Long employeeId) {

        return employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @GetMapping("/byStatus")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByStatus(
            @RequestParam(name = "status", required = false) Optional<String> statusOptional) {

        if (statusOptional.isPresent()) {
            return employeesRepository.findByStatus(Status.valueOf(statusOptional.get().toUpperCase()));
        } else {
            return employeesRepository.findAll();
        }
    }

    @GetMapping("/byDepartment")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByDepartment(
            @RequestParam(name = "department", required = false) Optional<String> departmentOptional) {

        if (departmentOptional.isPresent()) {
            return employeesRepository.findByDepartment(Department.valueOf(departmentOptional.get().toUpperCase()));
        } else {
            return employeesRepository.findAll();
        }
    }

}
