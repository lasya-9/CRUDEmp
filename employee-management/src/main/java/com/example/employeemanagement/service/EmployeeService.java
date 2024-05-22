package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(@Valid EmployeeDTO employeeDTO) {
        Employee employee = EmployeeDTO.getEmployee(employeeDTO);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EntityNotFoundException("Employee with given id : " + empId + " not found"));

    }

    public Employee updateEmployee(@NotNull(message = "invalid") Long empId, @Valid EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(() -> new EntityNotFoundException("Employee with given id : " + empId + " not found"));
        existingEmployee.setFirstName(employeeDTO.getFirstName());
        existingEmployee.setLastName(employeeDTO.getLastName());
        existingEmployee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new EntityNotFoundException("Employee with given id : " + empId + " not found"));
        employeeRepository.delete(employee);
    }
}
