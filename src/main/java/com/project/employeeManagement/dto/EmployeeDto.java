package com.project.employeeManagement.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private int salary;

    private long departmentId;

    private String departmentName;

}
