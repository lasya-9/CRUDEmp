package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.util.ResponseStatus;
import com.example.employeemanagement.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static com.example.employeemanagement.constant.Constant.*;

@RestController
@Tag(name = "Employee", description = "List of all APIs related to Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * This method is used to Add Employees
     *
     * @param employeeDTO EmployeeDTO
     * @return Employee
     */
    @Operation(summary = "Add Employee", description = "Add Employee", tags = {
            "Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @PostMapping("/employee")
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.addEmployee(employeeDTO);
        return ResponseUtils.response(EMPLOYEE_CREATED_SUCCESSFULLY, HttpStatus.CREATED, employee.getEmpId());
    }

    /**
     * This method is used to get list of all Employees
     *
     * @return List<Employee>
     */
    @Operation(summary = "Get list of Employees", description = "Get list of Employees", tags = {
            "Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @GetMapping("/employees")
    public ResponseEntity<Object> getAll() {
        return ResponseUtils.response(LIST_EMPLOYEES, HttpStatus.OK, employeeService.getAllEmployees());
    }

    /**
     * This method is used to get Employee by ID
     *
     * @param empId Long
     * @return Employee
     */
    @Operation(summary = "Get Employee by Id", description = "Get Employee by Id", tags = {
            "Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(schema = @Schema(implementation = EmployeeDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Employee Not Found", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @GetMapping("/employee/{empId}")
    public ResponseEntity<Object> getEmployee(@PathVariable Long empId) {
        return ResponseUtils.response(EMPLOYEE_DETAILS, HttpStatus.OK, employeeService.getEmployeeById(empId));
    }

    /**
     * This method is used to Update Employee
     *
     * @param empId       Long
     * @param employeeDTO EmployeeDTO
     * @return ResponseStatus
     */
    @Operation(summary = "Update Employee", description = "Update Employee", tags = {
            "Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Employee Not Found", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @PutMapping("/employee/{empId}")
    public ResponseEntity<Object> editEmployee(@PathVariable Long empId, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(empId, employeeDTO);
        return ResponseUtils.response(EMPLOYEE_UPDATED_SUCCESSFULLY, HttpStatus.OK);
    }

    /**
     * This method is used to Delete Employees
     *
     * @param empId Long
     * @return ResponseStatus
     */
    @Operation(summary = "Delete Employee", description = "Delete Employee", tags = {
            "Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Employee Not Found", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = {
                    @Content(schema = @Schema(implementation = ResponseStatus.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
        return ResponseUtils.response(EMPLOYEE_DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
