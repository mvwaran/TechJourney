package com.mvwaran.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assets")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String category;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    private EmployeeEntity employeeEntity;
}
