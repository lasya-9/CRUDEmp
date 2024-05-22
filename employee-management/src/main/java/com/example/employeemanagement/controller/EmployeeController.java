package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.employeemanagement.constant.Constant.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.addEmployee(employeeDTO);
        return ResponseUtils.response(EMPLOYEE_CREATED_SUCCESSFULY, HttpStatus.CREATED, employee.getEmpId());
    }

    @GetMapping("/employees")
    public ResponseEntity getAll() {
        return ResponseUtils.response(LIST_EMPLOYEES, HttpStatus.OK, employeeService.getAllEmployees());
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity getEmployee(@PathVariable Long empId) {
        return ResponseUtils.response(EMPLOYEE_DETAILS, HttpStatus.OK, employeeService.getEmployeeById(empId));
    }

    @PutMapping("/employee/{empId}")
    public ResponseEntity editEmployee(@PathVariable Long empId, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(empId, employeeDTO);
        return ResponseUtils.response(EMPLOYEE_UPDATED_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{empId}")
    public ResponseEntity deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
        return ResponseUtils.response(EMPLOYEE_DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
