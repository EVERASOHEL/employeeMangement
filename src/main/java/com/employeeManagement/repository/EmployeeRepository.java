package com.employeeManagement.repository;

import com.employeeManagement.model.Employee;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into tbl_employee (employeename,salary,department) values (:#{#emp.employeeName},:#{#emp.salary},:#{#emp.department})", nativeQuery = true)
    int saveEmployee(@Param("emp") Employee emp);

    @Query(value = "select * from tbl_employee", nativeQuery = true)
    List<Employee> findAllEmployee();

    @Transactional
    @Modifying
    @Query(value = "UPDATE tbl_employee SET employeename = :#{#emp.employeeName}, salary = :#{#emp.salary}, department = :#{#emp.department} WHERE employeeid = :employeeId", nativeQuery = true)
    int updateEmployee(@Param("emp") Employee emp,Long employeeId);

    @Transactional
    @Modifying
    @Query(value = "delete from tbl_employee where employeeid=:employeeid", nativeQuery = true)
    int deleteEmployeeByEmployeeId(@Param("employeeid") Long employeeId);

    @Query(value = "select * from tbl_employee where salary > :empSalary", nativeQuery = true)
    List<Employee> findByEmployeeSalaryGreaterThan(@Param("empSalary") Float employeeSalary);

    @Override
    boolean existsById(Long employeeId);
}
