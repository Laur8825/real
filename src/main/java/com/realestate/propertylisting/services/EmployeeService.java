package com.realestate.propertylisting.services;

import com.realestate.propertylisting.models.dtos.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    Optional<EmployeeDTO> getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

    void deleteEmployee(Long id);
}