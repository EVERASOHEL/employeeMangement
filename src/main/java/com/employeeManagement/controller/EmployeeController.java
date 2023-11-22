package com.employeeManagement.controller;

import com.employeeManagement.model.Employee;
import com.employeeManagement.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public String saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployee(){
    	return employeeService.findAllEmployee();
    }
    
    @PostMapping("/update/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId,@RequestBody Employee employee){
    	return employeeService.updateEmployeeByEmployeeId(employee,employeeId);
    }
    
    @PostMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
    	return employeeService.deleteEmployeeByemployeeId(employeeId);
    }

    @GetMapping("/list/{empSalary}")
    public ResponseEntity<?> findEmployeesBasedOnSalaryFilter(@PathVariable String empSalary){
        return employeeService.findEmployeesBasedOnSalary(empSalary);
    }
}
