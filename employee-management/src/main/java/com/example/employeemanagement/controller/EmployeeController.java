package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/addEmployee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return service.addEmployee(employee);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAll(){
        return service.getAllEmployees();
    }

    @GetMapping("/getEmployeeById")
    public Employee getEmployee(@RequestParam Long empId){
        return service.getEmployeeById(empId);
    }

    @PutMapping("/updateEmployee")
    public Employee editEmployee(@RequestBody Employee employee){
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long empId){
        return service.deleteEmployee(empId);
    }
}
