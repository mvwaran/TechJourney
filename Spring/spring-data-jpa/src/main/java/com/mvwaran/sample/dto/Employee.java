package com.mvwaran.sample.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private Address address;
    private Role role;
    private List<Asset> assets;
}
