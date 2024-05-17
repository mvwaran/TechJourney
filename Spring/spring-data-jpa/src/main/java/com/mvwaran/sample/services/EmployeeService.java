package com.mvwaran.sample.services;

import com.mvwaran.sample.entities.EmployeeEntity;
import com.mvwaran.sample.entities.ProjectEntity;
import com.mvwaran.sample.models.Employee;
import com.mvwaran.sample.models.Project;
import com.mvwaran.sample.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> readAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> Employee.builder()
                        .id(employeeEntity.getId())
                        .email(employeeEntity.getEmail())
                        .firstName(employeeEntity.getFirstName())
                        .lastName(employeeEntity.getLastName())
                        .project(Project.builder()
                                .id(employeeEntity.getProjectEntity().getId())
                                .projectName(employeeEntity.getProjectEntity().getProjectName())
                                .build())
                        .build())
                .toList();
    }

    public Employee create(Employee employee) {
        var employeeEntity = EmployeeEntity.builder()
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .projectEntity(ProjectEntity.builder()
                        .projectName(employee.getProject().getProjectName())
                        .build())
                .build();
        var updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return Employee.builder()
                .id(updatedEmployeeEntity.getId())
                .email(updatedEmployeeEntity.getEmail())
                .firstName(updatedEmployeeEntity.getFirstName())
                .lastName(updatedEmployeeEntity.getLastName())
                .project(Project.builder()
                        .id(updatedEmployeeEntity.getProjectEntity().getId())
                        .projectName(updatedEmployeeEntity.getProjectEntity().getProjectName())
                        .build())
                .build();
    }
}
