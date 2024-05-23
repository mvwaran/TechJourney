package com.mvwaran.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public EmployeeConfigData employeeConfigData() throws IOException {
        var jsonFile = new ClassPathResource("data.json").getFile();
        var employees = objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {});
        return EmployeeConfigData.builder()
                .employees(employees)
                .build();
    }
}
