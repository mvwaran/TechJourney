package com.mvwaran.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}
