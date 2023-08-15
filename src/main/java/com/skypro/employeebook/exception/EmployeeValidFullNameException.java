package com.skypro.employeebook.exception;

/**
 * Creates an exception indicating that the employee has an incorrect first name, last name
 */
public class EmployeeValidFullNameException extends RuntimeException{
    public EmployeeValidFullNameException(String message) {
        super(message);
    }
}
