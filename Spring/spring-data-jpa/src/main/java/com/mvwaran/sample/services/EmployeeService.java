package com.mvwaran.sample.services;

import com.mvwaran.sample.dto.Address;
import com.mvwaran.sample.dto.Employee;
import com.mvwaran.sample.dto.EmployeeRequest;
import com.mvwaran.sample.dto.Role;
import com.mvwaran.sample.entities.AddressEntity;
import com.mvwaran.sample.entities.EmployeeEntity;
import com.mvwaran.sample.entities.RoleEntity;
import com.mvwaran.sample.repositories.EmployeeRepository;
import com.mvwaran.sample.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Employee> readAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeEntityEntity -> Employee.builder()
                        .id(employeeEntityEntity.getId())
                        .firstName(employeeEntityEntity.getFirstName())
                        .lastName(employeeEntityEntity.getLastName())
                        .address(employeeEntityEntity.getAddressEntity() != null ? Address.builder()
                                .area(employeeEntityEntity.getAddressEntity().getArea())
                                .pinCode(employeeEntityEntity.getAddressEntity().getPinCode())
                                .build() : null)
                        .role(Role.builder()
                                .id(employeeEntityEntity.getRoleEntity().getId())
                                .name(employeeEntityEntity.getRoleEntity().getName())
                                .build())
                        .build())
                .toList();
    }

    public Employee create(EmployeeRequest employeeRequest) {
        RoleEntity roleEntity = roleRepository.findById(employeeRequest.getRoleId()).get();
        var employeeEntity = EmployeeEntity.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .addressEntity(employeeRequest.getAddress() != null ? AddressEntity.builder()
                        .area(employeeRequest.getAddress().getArea())
                        .pinCode(employeeRequest.getAddress().getPinCode())
                        .build() : null)
                .roleEntity(roleEntity)
                .build();
        employeeEntity.getAddressEntity().setEmployeeEntity(employeeEntity);
        var updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return Employee.builder()
                .id(updatedEmployeeEntity.getId())
                .firstName(updatedEmployeeEntity.getFirstName())
                .lastName(updatedEmployeeEntity.getLastName())
                .address(updatedEmployeeEntity.getAddressEntity() != null ? Address.builder()
                        .area(updatedEmployeeEntity.getAddressEntity().getArea())
                        .pinCode(updatedEmployeeEntity.getAddressEntity().getPinCode())
                        .build() : null)
                .role(Role.builder()
                        .id(updatedEmployeeEntity.getRoleEntity().getId())
                        .name(updatedEmployeeEntity.getRoleEntity().getName())
                        .build())
                .build();
    }
}
