package com.mvwaran.sample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
