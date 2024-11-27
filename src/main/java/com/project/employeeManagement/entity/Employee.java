package com.project.employeeManagement.entity;

import com.project.employeeManagement.dto.EmployeeDto;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private int salary;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public EmployeeDto getDto() {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(id);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setSalary(salary);
        dto.setDepartmentId(department.getId());
        dto.setDepartmentName(department.getName());
        return dto;
    }
}
