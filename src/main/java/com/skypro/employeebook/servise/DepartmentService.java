package com.skypro.employeebook.servise;
import com.skypro.employeebook.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeeByDepartment(Integer department) {

        return employeeService.getAllEmployees()
                .entrySet()
                .stream()
                .filter(employee -> department == null || employee.getValue().getDepartment().equals(department))
                .collect(Collectors.groupingBy(e -> e.getValue().getDepartment(), Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    @Override
    public Optional<Employee> getMaxSalaryInDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(e -> e.getSalary()));
    }

    @Override
    public Optional<Employee> getMinSalaryInDepartment(Integer department) {
        return employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(e -> e.getSalary()));
    }

}
