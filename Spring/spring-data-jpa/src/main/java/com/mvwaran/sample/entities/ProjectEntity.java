package com.mvwaran.sample.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String projectName;

//    @OneToOne(mappedBy = "projectEntity")
//    private EmployeeEntity employeeEntity;
}
