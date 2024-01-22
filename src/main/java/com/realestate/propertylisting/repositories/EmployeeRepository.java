package com.realestate.propertylisting.repositories;

import com.realestate.propertylisting.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by name
    List<Employee> findByNameContaining(String name);

    // Find an employee by email
    Optional<Employee> findByEmail(String email);
}