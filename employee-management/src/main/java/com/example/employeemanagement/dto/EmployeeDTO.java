package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.Employee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.employeemanagement.constant.Constant.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull(message = ERROR_INVALID_FIRST_NAME)
    @Pattern(regexp = REGEX_NAME, message = ERROR_INVALID_FIRST_NAME)
    private String firstName;

    @NotNull(message = ERROR_INVALID_FIRST_NAME)
    @Pattern(regexp = REGEX_NAME, message = ERROR_INVALID_LAST_NAME)
    private String lastName;

    @NotNull(message = ERROR_INVALID_SALARY)
    private double salary;

    public static Employee getEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }
}
