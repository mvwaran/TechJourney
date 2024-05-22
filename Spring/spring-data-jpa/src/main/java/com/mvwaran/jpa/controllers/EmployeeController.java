package com.mvwaran.jpa.controllers;

import com.mvwaran.jpa.dto.Employee;
import com.mvwaran.jpa.dto.EmployeeRequest;
import com.mvwaran.jpa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/readAll")
    public List<Employee> readAll() {
        return employeeService.readAll();
    }

    @PostMapping("create")
    public Employee create(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.create(employeeRequest);
    }
}
