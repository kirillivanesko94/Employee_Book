package com.skypro.employeebook.exception;

/**
 * Creates an exception indicating that the employee has not been found
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
