package com.mx.babel.employee.db.model;

import com.mx.babel.employee.db.model.enums.EventType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * ErrorLog entity that maps to the 'error_log' table in the database.
 */
@Entity
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT AUTO_INCREMENT COMMENT 'Unique identifier for each error log entry'")
    private Long id;

    /**
     * <p>Column: INT (nullable)</p>
     * The employee ID related to the error (nullable).
     */
    @Column(name = "employee_id", nullable = true)
    private Long employeeId;

    /**
     * <p>Column: ENUM('CREATE', 'UPDATE', 'DELETE')</p>
     * The type of event that caused the error (CREATE, UPDATE, DELETE).
     */
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('CREATE', 'UPDATE', 'DELETE')", nullable = true)
    private EventType eventType;

    /**
     * <p>Column: TEXT NOT NULL</p>
     * Message describing the error.
     */
    @Column(nullable = false, columnDefinition = "TEXT NOT NULL")
    private String errorMessage;

    /**
     * <p>Column: TEXT</p>
     * Stack trace of the error (optional).
     */
    @Column(columnDefinition = "TEXT")
    private String stackTrace;

    /**
     * <p>Column: DATETIME DEFAULT CURRENT_TIMESTAMP</p>
     * Date and time when the error occurred (automatically set).
     */
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime errorTimestamp;

    /**
     * <p>Column: VARCHAR(50)</p>
     * The IP address of the client who made the request (optional).
     */
    @Column(columnDefinition = "VARCHAR(50)", nullable = true)
    private String clientIp;

    /**
     * <p>Column: JSON</p>
     * Additional details or context related to the error (optional).
     */
    @Column(columnDefinition = "JSON", nullable = true)
    private String additionalDetails;

    /**
     * <p>Column: VARCHAR(100)</p>
     * The user or system component that caused the error.
     */
    @Column(columnDefinition = "VARCHAR(100)")
    private String source;


    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on the + sign on the left to edit the code.">

    /**
     * Default constructor for JPA.
     * Required for entity instantiation by frameworks.
     */
    public ErrorLog() {
    }

    /**
     * Constructor with all fields except 'id'.
     */
    public ErrorLog(Long employeeId, EventType eventType, String errorMessage, String stackTrace,
                    LocalDateTime errorTimestamp, String clientIp, String additionalDetails, String source) {
        this.employeeId = employeeId;
        this.eventType = eventType;
        this.errorMessage = errorMessage;
        this.stackTrace = stackTrace;
        this.errorTimestamp = errorTimestamp != null ? errorTimestamp : LocalDateTime.now(); // Default to current time if null
        this.clientIp = clientIp;
        this.additionalDetails = additionalDetails;
        this.source = source;
    }

    // </editor-fold>

    // Getters and Setters
    // <editor-fold defaultstate="collapsed" desc="Main getters and setters. Click on the + sign on the left to edit the code.">

    /**
     * Gets the unique identifier for the error log.
     *
     * @return the error log ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the error log.
     *
     * @param id the error log ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the employee related to the error.
     *
     * @return the employeeId associated with the error.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employeeId related to the error.
     *
     * @param employeeId the employee to set.
     */
    public void setEmployee(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the event type related to the error.
     *
     * @return the event type (CREATE, UPDATE, DELETE).
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Sets the event type related to the error.
     *
     * @param eventType the event type to set (CREATE, UPDATE, DELETE).
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the error message.
     *
     * @return the error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the stack trace of the error.
     *
     * @return the stack trace of the error, or null if not set.
     */
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * Sets the stack trace of the error.
     *
     * @param stackTrace the stack trace to set.
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * Gets the date and time when the error occurred.
     *
     * @return the timestamp of the error.
     */
    public LocalDateTime getErrorTimestamp() {
        return errorTimestamp;
    }

    /**
     * Sets the date and time when the error occurred.
     *
     * @param errorTimestamp the timestamp to set.
     */
    public void setErrorTimestamp(LocalDateTime errorTimestamp) {
        this.errorTimestamp = errorTimestamp;
    }

    /**
     * Gets the IP address of the client who made the request.
     *
     * @return the client IP address, or null if not set.
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * Sets the IP address of the client who made the request.
     *
     * @param clientIp the client IP address to set.
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * Gets additional details related to the error.
     *
     * @return additional details in JSON format, or null if not set.
     */
    public String getAdditionalDetails() {
        return additionalDetails;
    }

    /**
     * Sets additional details related to the error.
     *
     * @param additionalDetails the additional details to set.
     */
    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    /**
     * Gets the source of the error.
     *
     * @return the user or system component that caused the error.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the error.
     *
     * @param source the source to set.
     */
    public void setSource(String source) {
        this.source = source;
    }
    // </editor-fold>


    /**
     * Returns a string representation of the object, providing a summary of its state.
     * This is typically used for logging or debugging purposes.
     *
     * @return A string representing the current state of the object.
     */
    @Override
    public String toString() {
        return "ErrorLog{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", eventType=" + eventType.name() +
                ", errorMessage='" + errorMessage + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", errorTimestamp=" + errorTimestamp +
                ", clientIp='" + clientIp + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
