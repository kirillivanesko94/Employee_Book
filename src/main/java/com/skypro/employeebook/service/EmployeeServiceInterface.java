package com.skypro.employeebook.service;
import com.skypro.employeebook.model.Employee;
import java.util.Map;

/**
 * The EmployeeServiceInterface interface provides methods for managing employees.
 */
public interface  EmployeeServiceInterface {

    Employee addEmployee(String firstName, String lastName,
                         Integer passport, double salary, Integer department);
    Employee findEmployee(Integer passport);
    String removeEmployee(Integer passport);
    Map<Integer, Employee> getAllEmployees();
}
