package com.employeeManagement.validators;

import com.employeeManagement.constant.CommonConstant;
import com.employeeManagement.model.Employee;
import com.employeeManagement.validation.Validation;

import java.util.function.Function;

import static com.employeeManagement.validators.EmployeeRegistrationValidator.ValidationResult;

public interface EmployeeRegistrationValidator extends Function<Employee,ValidationResult> {

    static EmployeeRegistrationValidator isEmployeeNameValid() {
        return employee ->
                Validation.IsnullOrEmptyValue(ValidationResult.EMPLOYEE_NAME_MESSAGE.getMessage(), employee.getEmployeeName()) ?
                        ValidationResult.SUCCESS : ValidationResult.EMPLOYEE_NAME_MESSAGE;
    }

    static EmployeeRegistrationValidator isEmployeeSalaryValid(){
        return employee ->
                Validation.IsnullOrEmptyOrisNotDigitValue(ValidationResult.SALARY_MESSAGE.getMessage(), String.valueOf(employee.getSalary())) ?
                        ValidationResult.SUCCESS : ValidationResult.SALARY_MESSAGE;
    }

    static EmployeeRegistrationValidator isDepartmentNameValid(){
        return employee ->
                Validation.IsnullOrEmptyValue(ValidationResult.DEPARTMENT_NAME_MESSAGE.getMessage(), employee.getDepartment()) ?
                        ValidationResult.SUCCESS : ValidationResult.DEPARTMENT_NAME_MESSAGE;
    }

    default EmployeeRegistrationValidator and(EmployeeRegistrationValidator other){
        return employee -> {
            ValidationResult result = this.apply(employee);
            return result.equals(ValidationResult.SUCCESS) ? other.apply(employee) : result;
        };
    }

    enum ValidationResult{

        SUCCESS("SUCCESS"),
        EMPLOYEE_NAME_MESSAGE("Please enter the employee name."),
        DEPARTMENT_NAME_MESSAGE("Please enter the department name."),
        SALARY_MESSAGE("Please enter a valid salary value.");
        
        private final String message;
        ValidationResult(String message) {
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }

}
