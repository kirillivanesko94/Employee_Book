package com.skypro.employeebook.service;
import com.skypro.employeebook.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for working with department reports
 */
@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Method for to get all employees in the specified department
     * @param department ID department
     * @return map of employee
     */
    @Override
    public Map<Integer, List<Employee>> getAllEmployeeByDepartment(Integer department) {

        return employeeService.getAllEmployees()
                .entrySet()
                .stream()
                .filter(employee -> department == null || employee.getValue().getDepartment().equals(department))
                .collect(Collectors.groupingBy(e -> e.getValue().getDepartment(), Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    /**
     * Method for obtaining an employee with the maximum salary in the specified department
     * @param department ID department
     * @return Optional employee with MAX salary in department
     */
    @Override
    public Optional<Employee> getMaxSalaryInDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(e -> e.getSalary()));
    }

    /**
     * Method for obtaining an employee with the minimum salary in the specified department
     * @param department ID department
     * @return Optional employee with MIN salary in department
     */

    @Override
    public Optional<Employee> getMinSalaryInDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(e -> e.getSalary()));
    }

}
