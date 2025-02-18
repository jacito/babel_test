package com.mx.babel.employee.exception;

/**
 * Represents an error response when an employee is not found or there is an issue with the request.
 * This class is used to send structured error messages in the response.
 */
public class ErrorEmployee {

    /**
     * Error message that describes the issue with the employee data or request.
     */
    private String errorMessage;

    /**
     * An optional error code that can be used to categorize or further specify the type of error.
     * It can be used to map to different types of error categories.
     */
    private String errorCode;

    /**
     * Constructor to create an ErrorEmployee with just an error message.
     *
     * @param errorMessage the message describing the error.
     */
    public ErrorEmployee(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor to create an ErrorEmployee with both an error message and an error code.
     *
     * @param errorMessage the message describing the error.
     * @param errorCode    the code categorizing the type of error.
     */
    public ErrorEmployee(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message describing the issue.
     *
     * @return the error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message describing the issue.
     *
     * @param errorMessage the error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error code that categorizes the type of error.
     *
     * @return the error code.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code that categorizes the type of error.
     *
     * @param errorCode the error code to set.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
