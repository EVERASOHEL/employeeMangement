package com.employeeManagement.service;

import com.employeeManagement.model.Employee;
import com.employeeManagement.repository.EmployeeRepository;
import com.employeeManagement.validation.AppException;
import com.employeeManagement.validation.Validation;

import com.employeeManagement.validators.EmployeeRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static com.employeeManagement.validators.EmployeeRegistrationValidator.*;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String saveEmployee(Employee employee) {

        try {
            employeeValidation(employee);
            return employeeRepository.saveEmployee(employee) > 0 ? "Data successfully inserted." : "Data not inserted.";
        } catch (AppException e) {
            return e.getMessage();
        }
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    public String updateEmployeeByEmployeeId(Employee employee, String employeeId) {

        try {
            Validation.IsnullOrEmptyOrisNotDigit("Please enter valid employeeId.", employeeId);
            if (employeeRepository.existsById(Long.parseLong(employeeId))) {
                employeeValidation(employee);
                return employeeRepository.updateEmployee(employee, Long.parseLong(employeeId)) > 0 ? "Data successfully updated." : "Data not updated.";
            } else {
                throw new AppException("employee Id does not exist.");
            }
        } catch (AppException e) {
            return e.getMessage();
        }

    }

    public String deleteEmployeeByemployeeId(String employeeId) {

        try {
            Validation.IsnullOrEmptyOrisNotDigit("Please enter the valid employeeId.", employeeId);
            return employeeRepository.deleteEmployeeByEmployeeId(Long.parseLong(employeeId)) > 0 ? "Data successfully deleted." : "Data not deleted.";
        } catch (AppException e) {
            return e.getMessage();
        }

    }

    public ResponseEntity<?> findEmployeesBasedOnSalary(String employeeSalary) {
        try {
            Validation.IsnullOrEmptyOrisNotDigit("Please enter a valid salary value.", employeeSalary);
            return ResponseEntity.ok(employeeRepository.findByEmployeeSalaryGreaterThan(Float.parseFloat(employeeSalary)));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public void employeeValidation(Employee employee) throws AppException {
        ValidationResult validationResult =
                isEmployeeNameValid()
                .and(isEmployeeSalaryValid())
                .and(isDepartmentNameValid())
                .apply(employee);
        if(validationResult!=ValidationResult.SUCCESS){
            throw new AppException(validationResult.getMessage());
        }
//        Validation.IsnullOrEmpty("Please enter the employee name.", employee.getEmployeeName());
//        Validation.IsnullOrEmptyOrisNotDigit("Please enter a valid salary value.", String.valueOf(employee.getSalary()));
//        Validation.IsnullOrEmpty("Please enter the department name.", employee.getDepartment());
    }
}
