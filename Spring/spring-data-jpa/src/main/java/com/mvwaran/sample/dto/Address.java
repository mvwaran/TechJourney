package com.mvwaran.sample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String doorNo;
    private String street;
    private String area;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}
