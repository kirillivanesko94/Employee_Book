package com.skypro.employeebook.servise;
import com.skypro.employeebook.model.Employee;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentServiceInterface {

    Map<Integer, List <Employee>> getAllEmployeeByDepartment(Integer department);

    Optional<Employee> getMaxSalaryInDepartment(Integer department);

    Optional<Employee> getMinSalaryInDepartment(Integer department);
}
