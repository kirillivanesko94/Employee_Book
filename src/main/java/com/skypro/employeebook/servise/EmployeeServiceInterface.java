package com.skypro.employeebook.servise;
import com.skypro.employeebook.model.Employee;
import java.util.Map;
public interface  EmployeeServiceInterface {

    Employee addEmployee(String firstName, String lastName,
                         Integer passport, double salary, Integer department);
    Employee findEmployee(Integer passport);
    String removeEmployee(Integer passport);
    Map<Integer, Employee> getAllEmployees();
}
