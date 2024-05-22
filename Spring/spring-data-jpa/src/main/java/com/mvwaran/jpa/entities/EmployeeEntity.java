package com.mvwaran.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AddressEntity addressEntity;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity roleEntity;

    @OneToMany(mappedBy = "employeeEntity")
    private List<AssetEntity> assetEntities;
}
