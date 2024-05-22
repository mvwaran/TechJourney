package com.mvwaran.jpa.services;

import com.mvwaran.jpa.constants.AssetCategory;
import com.mvwaran.jpa.dto.*;
import com.mvwaran.jpa.entities.AddressEntity;
import com.mvwaran.jpa.entities.EmployeeEntity;
import com.mvwaran.jpa.entities.RoleEntity;
import com.mvwaran.jpa.repositories.EmployeeRepository;
import com.mvwaran.jpa.repositories.RoleRepository;
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
                        .name(employeeEntityEntity.getName())
                        .address(employeeEntityEntity.getAddressEntity() != null ? Address.builder()
                                .area(employeeEntityEntity.getAddressEntity().getArea())
                                .pinCode(employeeEntityEntity.getAddressEntity().getPinCode())
                                .build() : null)
                        .role(Role.builder()
                                .id(employeeEntityEntity.getRoleEntity().getId())
                                .name(employeeEntityEntity.getRoleEntity().getName())
                                .build())
                        .assets(employeeEntityEntity.getAssetEntities().stream()
                                .map(assetEntity -> Asset.builder()
                                        .id(assetEntity.getId())
                                        .category(AssetCategory.valueOf(assetEntity.getCategory()))
                                        .name(assetEntity.getName())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }

    public Employee create(EmployeeRequest employeeRequest) {
        RoleEntity roleEntity = roleRepository.findById(employeeRequest.getRoleId()).get();
        var employeeEntity = EmployeeEntity.builder()
                .name(employeeRequest.getName())
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
                .name(updatedEmployeeEntity.getName())
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
