package com.mvwaran.sample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private Address address;
    private String roleId;
}
