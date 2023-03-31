package com.skypro.employeebook.servise;

import com.skypro.employeebook.exception.DepartmentNotFoundException;
import com.skypro.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getAllEmployeeByDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(employee -> department == null || employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees()
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getValue().getDepartment(), Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    @Override
    public double getMaxSalaryInDepartment(Integer department) {
        Employee employee = employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(e -> e.getSalary()))
                .orElseThrow(()-> new DepartmentNotFoundException("Департамент не найден"));
        return employee.getSalary();
    }

    @Override
    public double getMinSalaryInDepartment(Integer department) {
        Employee employee = employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(e -> e.getSalary()))
                .orElseThrow(()-> new DepartmentNotFoundException("Департамент не найден"));
        return employee.getSalary();
    }

    @Override
    public double getSumSalaryInDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .mapToInt(e -> (int) e.getSalary()).sum();
    }


}
