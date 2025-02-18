package com.mx.babel.employee.exception;

/**
 * Custom exception to represent an unknown or uncontrolled error.
 * This exception is used to wrap any unexpected or unknown exceptions.
 */
public class UnknownErrorException extends RuntimeException {

    /**
     * Constructor that accepts the original exception.
     *
     * @param cause the original exception that caused the error.
     */
    public UnknownErrorException(Throwable cause) {
        super("An unknown or uncontrolled error occurred: " + cause.getMessage(), cause);
    }

    /**
     * Constructor that allows providing a custom message for the unknown error.
     *
     * @param message the custom message describing the error.
     * @param cause   the original exception that caused the error.
     */
    public UnknownErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
