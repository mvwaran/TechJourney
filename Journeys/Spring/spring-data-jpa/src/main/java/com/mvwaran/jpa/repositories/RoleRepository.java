package com.mvwaran.jpa.repositories;

import com.mvwaran.jpa.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
