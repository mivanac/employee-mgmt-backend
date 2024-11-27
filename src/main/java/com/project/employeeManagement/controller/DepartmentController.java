package com.project.employeeManagement.controller;

import com.project.employeeManagement.dto.DepartmentDto;
import com.project.employeeManagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/management")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDtoList);
    }

    @PostMapping("/add-department")
    public ResponseEntity<DepartmentDto> addDepartments(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentDto1 = departmentService.addDepartments(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentDto1);
    }

}
