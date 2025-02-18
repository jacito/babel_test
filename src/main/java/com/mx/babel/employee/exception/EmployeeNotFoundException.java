package com.mx.babel.employee.exception;

/**
 * Custom exception to handle employee not found scenario.
 * This exception will be thrown when an employee is not found in the system.
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     *
     * @param message the custom error message.
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
