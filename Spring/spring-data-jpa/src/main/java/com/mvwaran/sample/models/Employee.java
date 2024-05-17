package com.mvwaran.sample.models;

import com.mvwaran.sample.entities.ProjectEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Project project;
}
