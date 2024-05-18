package com.mvwaran.sample.repositories;

import com.mvwaran.sample.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
