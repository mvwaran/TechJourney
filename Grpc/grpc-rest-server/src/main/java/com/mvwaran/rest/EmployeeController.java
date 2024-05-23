package com.mvwaran.rest;

import com.mvwaran.common.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private RestEmployeeService restEmployeeService;

    @GetMapping("/employees/readAll")
    public Flux<Employee> readAll() {
        return restEmployeeService.readAll();
    }
}
