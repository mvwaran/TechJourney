package com.mvwaran.rest;

import com.mvwaran.common.Employee;
import com.mvwaran.common.EmployeeConfigData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
public class RestEmployeeService {

    @Autowired
    private EmployeeConfigData employeeConfigData;

    public Flux<Employee> readAll() {
        log.info("Tjhvcadnakls");
        return Flux.fromIterable(employeeConfigData.getEmployees());
    }
}
