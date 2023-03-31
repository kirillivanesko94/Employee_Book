package com.skypro.employeebook;

import com.skypro.employeebook.exception.DepartmentNotFoundException;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.servise.DepartmentServiceImpl;
import com.skypro.employeebook.servise.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)

public class DepartmentServiceTest {
    @Autowired
    DepartmentServiceImpl departmentService;
    @MockBean
    EmployeeServiceImpl employeeService;
    private static final Employee employeeWithMinSalary = new Employee("Ivan", "Ivanov", 123, 5_000, 1);
    private static final Employee employeeWithMaxSalary = new Employee("Petr", "Petrov", 1234, 6_000, 1);
    private static final Employee vasya = new Employee("Vasya", "Vasya", 1235, 16_000, 2);
    private static final Map<Integer, Employee> employeeMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        employeeMap.put(employeeWithMinSalary.getPassport(), employeeWithMinSalary);
        employeeMap.put(employeeWithMaxSalary.getPassport(), employeeWithMaxSalary);
        employeeMap.put(vasya.getPassport(), vasya);
    }

    public static Stream<Arguments> getDepartment() {
        return Stream.of(Arguments.of(employeeWithMinSalary.getDepartment()),
                Arguments.of(employeeWithMaxSalary.getDepartment()));

    }

    @ParameterizedTest
    @MethodSource("getDepartment")
    void checkGetAllEmployeesByDepartment(Integer department) {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        List<Employee> expected = List.of(employeeWithMaxSalary, employeeWithMinSalary);
        List<Employee> actual = departmentService.getAllEmployeeByDepartment(department);
        assertEquals(expected, actual);
    }

    @Test
    void checkGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        List<Employee> employeeList1 = List.of(employeeWithMaxSalary, employeeWithMinSalary);
        List<Employee> employeeList2 = List.of(vasya);
        Map<Integer, List<Employee>> expected = new HashMap<>();
        expected.put(employeeWithMaxSalary.getDepartment(), employeeList1);
        expected.put(vasya.getDepartment(), employeeList2);

        Map<Integer, List<Employee>> actual = departmentService.getAllEmployees();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getDepartment")
    void checkGetMaxSalaryInDepartment(Integer department) {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        double actual = departmentService.getMaxSalaryInDepartment(department);
        assertEquals(employeeWithMaxSalary.getSalary(), actual);


    }

    @ParameterizedTest
    @MethodSource("getDepartment")
    void checkGetMinSalaryInDepartment(Integer department) {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        double actual = departmentService.getMinSalaryInDepartment(department);
        assertEquals(employeeWithMinSalary.getSalary(), actual);


    }

    @ParameterizedTest
    @MethodSource("getDepartment")
    void checkGetSumInDepartment(Integer department) {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        double sum = employeeService.getAllEmployees()
                .values()
                .stream()
                .filter(e -> e.getDepartment().equals(department))
                .mapToInt(e -> (int) e.getSalary()).sum();

        double actual = departmentService.getSumSalaryInDepartment(department);

        assertEquals(sum, actual);

    }

    @Test
    void checkGetMaxSalaryInDepartmentException() {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
               Exception exception = assertThrows(DepartmentNotFoundException.class,
                () ->  departmentService.getMaxSalaryInDepartment(8));
        String expectedException = "Департамент не найден";
        assertEquals(expectedException, exception.getMessage());
    }
    @Test
    void checkGetMinSalaryInDepartmentException() {
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);
        Exception exception = assertThrows(DepartmentNotFoundException.class,
                () ->  departmentService.getMinSalaryInDepartment(8));
        String expectedException = "Департамент не найден";
        assertEquals(expectedException, exception.getMessage());
    }
}
