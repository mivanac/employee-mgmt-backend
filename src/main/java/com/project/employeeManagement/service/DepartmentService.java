package com.project.employeeManagement.service;

import com.project.employeeManagement.dto.DepartmentDto;
import com.project.employeeManagement.entity.Department;
import com.project.employeeManagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream().map(Department::getDto).collect(Collectors.toList());
    }

    public DepartmentDto addDepartments(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        Department department1 = departmentRepository.save(department);
        return department1.getDto();
    }
}
