package com.project.employeeManagement.controller;

import com.project.employeeManagement.dto.EmployeeDto;
import com.project.employeeManagement.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/management")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtoList);
    }

    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) throws IOException {
        try {
            EmployeeDto employeeDto1 = employeeService.addEmployee(employeeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto1);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee with ID " + id + " deleted successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeService.getEmployee(id);
        if (employeeDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDto);
    }


    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        if (updatedEmployee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/employeesByDepartment/{id}")
    public ResponseEntity<?> getEmployeesByDepartment(@PathVariable Long id) {
        List<EmployeeDto> employeeDtoList = employeeService.getEmployeesPerDepartment(id);
        if (employeeDtoList == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDtoList);
    }
}
