package com.mvwaran.sample.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class RoleEntity {

    @Id
    private String id;

    private String name;
}
