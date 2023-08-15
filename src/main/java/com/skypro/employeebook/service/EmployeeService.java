package com.skypro.employeebook.service;

import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeValidFullNameException;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for working with the employee entity
 */

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final int MAX_COUNT = 3;
    private final Map<Integer, Employee> employees = new HashMap<>();

    /**
     * Method for adding an employee to the map
     * @param firstName Employee's name
     * @param lastName Employee's last name
     * @param passport Employee's passport
     * @param salary Employee's salary
     * @param department Employee's department
     * @return {@link Employee}
     */
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

    /**
     * The method of verifying the validity of the surname and first name
     * @param firstName Employee's name
     * @param lastName Employee's last name
     */

    public void checkValidFullName(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new EmployeeValidFullNameException("Неверный формат ФИО");
        }
    }

    /**
     * The method of searching for an employee by passport
     * @param passport Employee's passport
     * @return {@link Employee}
     */

    @Override
    public Employee findEmployee(Integer passport) {
        if (employees.get(passport) == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(passport);
    }

    /**
     * Method for deleting employees
     * @param passport Employee's passport
     * @return A line informing about the successful removal of an employee
     */

    @Override
    public String removeEmployee(Integer passport) {
        Employee employee = findEmployee(passport);
        if (employees.get(passport) == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(passport);
        return String.format("Сотрудник %s %s удален", employee.getFirstName(), employee.getLastName());
    }

    /**
     * Method for getting all employees
     */

    @Override
    public Map<Integer, Employee> getAllEmployees() {
        return employees;
    }

}
