package com.mvwaran.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {
    private String name;
    private Address address;
    private String roleId;
}
