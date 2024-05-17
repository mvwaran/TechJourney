package com.mvwaran.sample.services;

import com.mvwaran.sample.dto.Address;
import com.mvwaran.sample.dto.Employee;
import com.mvwaran.sample.dto.Project;
import com.mvwaran.sample.entities.AddressEntity;
import com.mvwaran.sample.entities.EmployeeEntity;
import com.mvwaran.sample.entities.ProjectEntity;
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
                .map(employeeEntityEntity -> Employee.builder()
                        .id(employeeEntityEntity.getId())
                        .firstName(employeeEntityEntity.getFirstName())
                        .lastName(employeeEntityEntity.getLastName())
                        .address(employeeEntityEntity.getAddressEntity() != null ? Address.builder()
                                .doorNo(employeeEntityEntity.getAddressEntity().getDoorNo())
                                .street(employeeEntityEntity.getAddressEntity().getStreet())
                                .area(employeeEntityEntity.getAddressEntity().getArea())
                                .city(employeeEntityEntity.getAddressEntity().getCity())
                                .state(employeeEntityEntity.getAddressEntity().getState())
                                .country(employeeEntityEntity.getAddressEntity().getCountry())
                                .pinCode(employeeEntityEntity.getAddressEntity().getPinCode())
                                .build() : null)
                        .build())
                .toList();
    }

    public Employee create(Employee employee) {
        var employeeEntity = EmployeeEntity.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .addressEntity(employee.getAddress() != null ? AddressEntity.builder()
                        .doorNo(employee.getAddress().getDoorNo())
                        .street(employee.getAddress().getStreet())
                        .area(employee.getAddress().getArea())
                        .city(employee.getAddress().getCity())
                        .state(employee.getAddress().getState())
                        .country(employee.getAddress().getCountry())
                        .pinCode(employee.getAddress().getPinCode())
                        .build() : null)
                .build();
        var updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return Employee.builder()
                .id(updatedEmployeeEntity.getId())
                .firstName(updatedEmployeeEntity.getFirstName())
                .lastName(updatedEmployeeEntity.getLastName())
                .address(updatedEmployeeEntity.getAddressEntity() != null ? Address.builder()
                        .doorNo(updatedEmployeeEntity.getAddressEntity().getDoorNo())
                        .street(updatedEmployeeEntity.getAddressEntity().getStreet())
                        .area(updatedEmployeeEntity.getAddressEntity().getArea())
                        .city(updatedEmployeeEntity.getAddressEntity().getCity())
                        .state(updatedEmployeeEntity.getAddressEntity().getState())
                        .country(updatedEmployeeEntity.getAddressEntity().getCountry())
                        .pinCode(updatedEmployeeEntity.getAddressEntity().getPinCode())
                        .build() : null)
                .build();
    }
}
