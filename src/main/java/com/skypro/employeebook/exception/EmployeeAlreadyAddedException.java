package com.skypro.employeebook.exception;

/**
 * Creates an exception indicating that an employee with the specified first and last name has already been added to the repository.
 */
public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(String firstName, String lastName) {
        super("Сотрудник: " + firstName + " " + lastName + " уже добавлен в хранилище");
    }
}
