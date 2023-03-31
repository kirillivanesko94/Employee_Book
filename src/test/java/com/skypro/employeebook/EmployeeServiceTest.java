package com.skypro.employeebook;

import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import com.skypro.employeebook.exception.EmployeeValidFullNameException;
import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.servise.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {EmployeeServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {


    EmployeeServiceImpl employeeService;
    private static final Employee ivan = new Employee("Ivan", "Ivanov", 123, 5_000, 1);
    private static final Employee petr = new Employee("Petr", "Petrov", 1234, 6_000, 1);

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee(ivan.getFirstName(), ivan.getLastName(), ivan.getPassport(), ivan.getSalary(), ivan.getDepartment());
        employeeService.addEmployee(petr.getFirstName(), petr.getLastName(), petr.getPassport(), petr.getSalary(), petr.getDepartment());
    }

    public static Stream<Arguments> getPassport() {
        return Stream.of(Arguments.of(ivan.getPassport(), ivan),
                Arguments.of(petr.getPassport(), petr));

    }

    @Test
    void checkGetAllEmployees() {
        Map<Integer, Employee> actual = employeeService.getAllEmployees();

        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(ivan.getPassport(), ivan);
        expected.put(petr.getPassport(), petr);


        assertEquals(expected, actual);
    }

    @Test
    void checkAddEmployeeSuccess() {
        Employee employee1 = employeeService.addEmployee("Ivan", "Petrov", 12, 12_000, 2);

        Map<Integer, Employee> actual = employeeService.getAllEmployees();
        Map<Integer, Employee> expected = new HashMap<>();
        expected.put(ivan.getPassport(), ivan);
        expected.put(petr.getPassport(), petr);
        expected.put(employee1.getPassport(),employee1);


        assertEquals(expected,actual);
        assertTrue(actual.containsKey(employee1.getPassport()));
    }

    @ParameterizedTest
    @MethodSource("getPassport")
    void checkEmployeeFindSuccess(Integer passport, Employee expected) {
        assertEquals(expected, employeeService.findEmployee(passport));
    }

    @ParameterizedTest
    @MethodSource("getPassport")
    void checkEmployeeRemoveSuccess(Integer passport) {
        employeeService.removeEmployee(passport);
        Map<Integer, Employee> actual = employeeService.getAllEmployees();
        assertEquals(1, actual.size());
        assertNull(actual.get(passport));
    }

    @Test
    void checkEmployeeAlreadyAddedException() {
        Exception exception = assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Ivan", "Ivanov", 123, 5_000, 1));
        String expectedException = "Сотрудник: Ivan Ivanov уже добавлен в хранилище";
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void checkEmployeeValidFullNameException() {
        Exception exception = assertThrows(EmployeeValidFullNameException.class,
                () -> employeeService.addEmployee("Ivan", "123123", 123, 5_000, 1));
        String expectedException = "Неверный формат ФИО";
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void checkEmployeeNotFoundException() {
        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.findEmployee(123123));
        String expectedException = "Сотрудник не найден";
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void checkEmployeeStorageIsFullException() {
        employeeService.addEmployee("Semen", "Petrov", 12345, 6_000, 1);
        Exception exception = assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("Semen", "Petrov", 12345, 6_000, 1));
        String expectedException = "Хранилище (3 элементов) полностью заполнено";
        assertEquals(expectedException, exception.getMessage());
    }


}
