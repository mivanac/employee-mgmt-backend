package com.project.employeeManagement.entity;

import com.project.employeeManagement.dto.DepartmentDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private long id;

    @Column(name = "department_name")
    private String name;

    public DepartmentDto getDto() {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}
