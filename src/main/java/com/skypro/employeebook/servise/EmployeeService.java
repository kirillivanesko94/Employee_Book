package com.skypro.employeebook.servise;

import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeValidFullNameException;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final int MAX_COUNT = 3;
    private final Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName,
                                Integer passport, double salary, Integer department) {
        checkValidFullName(firstName,lastName);
        if (employees.size() == MAX_COUNT) {
            throw new EmployeeStorageIsFullException(employees.size());
        }
        Employee employee = new Employee(firstName, lastName, passport, salary, department);
        StringUtils.capitalize(firstName);
        StringUtils.capitalize(lastName);
        if (employees.containsKey(passport)) {
            throw new EmployeeAlreadyAddedException(firstName, lastName);
        }
        employees.put(employee.getPassport(), employee);

        return employee;
    }

    public void checkValidFullName(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new EmployeeValidFullNameException("Неверный формат ФИО");
        }
    }

    @Override
    public Employee findEmployee(Integer passport) {
        if (employees.get(passport) == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(passport);
    }

    @Override
    public String removeEmployee(Integer passport) {
        Employee employee = findEmployee(passport);
        if (employees.get(passport) == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(passport);
        return String.format("Сотрудник %s %s удален", employee.getFirstName(), employee.getLastName());
    }

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return employees;
    }

}
