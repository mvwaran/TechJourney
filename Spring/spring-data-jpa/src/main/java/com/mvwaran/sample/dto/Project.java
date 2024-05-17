package com.mvwaran.sample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private Integer id;
    private String projectName;
}
