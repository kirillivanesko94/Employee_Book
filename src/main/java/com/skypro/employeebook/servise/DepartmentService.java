package com.skypro.employeebook.servise;

import com.skypro.employeebook.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> getAllEmployeeByDepartment(Integer department);

    Map<Integer, List<Employee>> getAllEmployees();

    double getMaxSalaryInDepartment(Integer department);

    double getMinSalaryInDepartment(Integer department);

    double getSumSalaryInDepartment(Integer department);


}
