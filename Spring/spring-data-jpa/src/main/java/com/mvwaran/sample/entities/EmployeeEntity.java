package com.mvwaran.sample.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeEntity")
    @PrimaryKeyJoinColumn
    private AddressEntity addressEntity;
}
