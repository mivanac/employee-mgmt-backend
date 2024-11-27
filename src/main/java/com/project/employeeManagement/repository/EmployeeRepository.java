package com.project.employeeManagement.repository;

import com.project.employeeManagement.entity.Department;
import com.project.employeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByDepartment(Department department);
}
