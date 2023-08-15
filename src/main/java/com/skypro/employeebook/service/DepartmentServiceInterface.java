package com.skypro.employeebook.service;
import com.skypro.employeebook.model.Employee;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This interface provides methods for retrieving information about employees in a department.
 */

public interface DepartmentServiceInterface {

    Map<Integer, List <Employee>> getAllEmployeeByDepartment(Integer department);

    Optional<Employee> getMaxSalaryInDepartment(Integer department);

    Optional<Employee> getMinSalaryInDepartment(Integer department);
}
