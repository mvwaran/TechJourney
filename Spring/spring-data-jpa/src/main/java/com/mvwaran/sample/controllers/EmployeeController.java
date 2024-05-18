package com.mvwaran.sample.controllers;

import com.mvwaran.sample.dto.Employee;
import com.mvwaran.sample.dto.EmployeeRequest;
import com.mvwaran.sample.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
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
