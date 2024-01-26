package com.realestate.propertylisting.services;

import com.realestate.propertylisting.models.dtos.EmployeeDTO;
import com.realestate.propertylisting.models.entities.Employee;
import com.realestate.propertylisting.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Converts Employee entity to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail()
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        return employee;
    }
}