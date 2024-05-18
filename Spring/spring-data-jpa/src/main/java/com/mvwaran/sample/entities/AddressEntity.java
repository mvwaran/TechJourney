package com.mvwaran.sample.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AddressEntity {

    @Id
    private Integer id;

    private String area;
    private String pinCode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private EmployeeEntity employeeEntity;
}
