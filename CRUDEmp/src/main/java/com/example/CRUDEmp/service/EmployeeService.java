package com.example.CRUDEmp.service;

import com.example.CRUDEmp.entity.Employee;
import com.example.CRUDEmp.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
       return repository.findById(empId).orElseThrow(()->new EntityNotFoundException("Employee with given id : "+empId));

    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = repository.getReferenceById(employee.getEmpId());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setSalary(employee.getSalary());
        return repository.save(existingEmployee);
    }

    public String deleteEmployee(Long empId) {
        repository.deleteById(empId);
        return "Employee with id : "+ empId+" deleted successfully";
    }
}
