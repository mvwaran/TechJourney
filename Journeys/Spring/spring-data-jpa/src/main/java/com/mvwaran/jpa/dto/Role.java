package com.mvwaran.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private String id;
    private String name;
}
