package com.project.employeeManagement.service;


import com.project.employeeManagement.dto.EmployeeDto;
import com.project.employeeManagement.entity.Department;
import com.project.employeeManagement.entity.Employee;
import com.project.employeeManagement.repository.DepartmentRepository;
import com.project.employeeManagement.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<EmployeeDto> getAllEmployees() {
      return employeeRepository.findAll().stream().map(Employee::getDto).collect(Collectors.toList());
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws IOException {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setSalary(employeeDto.getSalary());
        Optional<Department> department = departmentRepository.findById(employeeDto.getDepartmentId());
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department with ID " + employeeDto.getDepartmentId() + " not found");
        }
        employee.setDepartment(department.get());
        Employee employee1 = employeeRepository.save(employee);
        return employee1.getDto();
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeDto getEmployee(Long id) {
        return employeeRepository.findById(id).map(Employee::getDto).orElse(null);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee employeeExisting = employee.get();
            employeeExisting.setFirstName(employeeDto.getFirstName());
            employeeExisting.setLastName(employeeDto.getLastName());
            employeeExisting.setEmail(employeeDto.getEmail());
            employeeExisting.setSalary(employeeDto.getSalary());
            Department department = departmentRepository.findById(employeeDto.getDepartmentId()).orElse(null);
            if (department == null) {
                return null;
            }
            employeeExisting.setDepartment(department);
            return employeeRepository.save(employeeExisting).getDto();
        }
        return null;
    }

    public List<EmployeeDto> getEmployeesPerDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            return null;
        }
        return employeeRepository.findByDepartment(department).stream().map(Employee::getDto).collect(Collectors.toList());
    }

}
