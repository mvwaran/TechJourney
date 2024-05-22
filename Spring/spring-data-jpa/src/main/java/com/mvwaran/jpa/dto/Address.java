package com.mvwaran.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String area;
    private String pinCode;
}
