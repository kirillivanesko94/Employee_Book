package com.skypro.employeebook.exception;

/**
 * Creates an exception indicating that an employee cannot be added due to lack of storage space
 */
public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(int storageSize) {
        super("Хранилище (" + storageSize + " элементов) полностью заполнено");
    }
}
