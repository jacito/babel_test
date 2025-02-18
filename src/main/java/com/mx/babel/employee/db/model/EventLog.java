package com.mx.babel.employee.db.model;

import com.mx.babel.employee.db.model.enums.EventType;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity class to represent the event_log table.
 * This table stores event logs related to employee actions (CREATE, UPDATE, DELETE).
 */
@Entity
@Table(name = "event_log")
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>Column: INT (nullable)</p>
     * Employee ID that the action was applied to.
     * This column stores the ID of the employee affected by the event.
     * It may be null if the action is not directly related to a specific employee.
     */
    @Column(name = "employee_id", nullable = true)
    private Long employeeId;


    /**
     * <p>Column: ENUM() NOT NULL</p>
     * Type of event (CREATE, UPDATE, DELETE, CONSULT_BY_ID, CONSULT_ACTIVE, CONSULT_ALL).
     * This column stores the type of event that occurred.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    /**
     * <p>Column: DATETIME DEFAULT CURRENT_TIMESTAMP</p>
     * Date and time when the event occurred.
     * This column stores the timestamp of when the event took place.
     */
    @Column(name = "event_timestamp", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date eventTimestamp;

    /**
     * <p>Column: VARCHAR(50)</p>
     * IP address of the client who made the request (optional).
     * This column stores the IP address of the client who initiated the event.
     */
    @Column(name = "client_ip", length = 50)
    private String clientIp;

    /**
     * <p>Column: JSON</p>
     * Additional details related to the event (optional).
     * This column stores any additional information regarding the event, like affected fields or data.
     */
    @Column(name = "additional_details")
    private String additionalDetails;

    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on the + sign on the left to edit the code.">

    /**
     * Default constructor for JPA.
     * Required for entity instantiation by frameworks.
     */
    public EventLog() {
    }

    /**
     * Constructor to initialize basic fields
     *
     * @param employeeId        ID of the employee related to the event (nullable).
     * @param eventType         Type of event (CREATE, UPDATE, DELETE, etc.).
     * @param clientIp          IP address of the client making the request (optional).
     * @param additionalDetails Additional details in JSON or text format (optional).
     */
    public EventLog(Long employeeId, EventType eventType, String clientIp, String additionalDetails) {
        this.employeeId = employeeId;
        this.eventType = eventType;
        this.eventTimestamp = new Date();
        this.clientIp = clientIp;
        this.additionalDetails = additionalDetails;
    }

    /**
     * Constructor to initialize all fields except ID.
     * The event timestamp is automatically set to the current date and time.
     *
     * @param employeeId ID of the employee related to the event (nullable).
     * @param eventType  Type of event (CREATE, UPDATE, DELETE, etc.).
     * @param clientIp   IP address of the client making the request (optional).
     */
    public EventLog(Long employeeId, EventType eventType, String clientIp) {
        this.employeeId = employeeId;
        this.eventType = eventType;
        this.clientIp = clientIp;
        this.eventTimestamp = new Date();
    }

    /**
     * Constructor to initialize only eventType , clientIp and additionalDetails .
     * The event timestamp is automatically set to the current date and time.
     *
     * @param eventType Type of event (CREATE, UPDATE, DELETE, etc.).
     * @param clientIp  IP address of the client making the request (optional).
     */
    public EventLog(EventType eventType, String clientIp, String additionalDetails) {
        this.eventType = eventType;
        this.clientIp = clientIp;
        this.additionalDetails = additionalDetails;
        this.eventTimestamp = new Date();
    }

    /**
     * Constructor to initialize only eventType and clientIp.
     * The event timestamp is automatically set to the current date and time.
     *
     * @param eventType Type of event (CREATE, UPDATE, DELETE, etc.).
     * @param clientIp  IP address of the client making the request (optional).
     */
    public EventLog(EventType eventType, String clientIp) {
        this.eventType = eventType;
        this.clientIp = clientIp;
        this.eventTimestamp = new Date();
    }

    // </editor-fold>

    // Getters and Setters
    // <editor-fold defaultstate="collapsed" desc="Main getters and setters. Click on the + sign on the left to edit the code.">

    /**
     * Gets the unique identifier of the event.
     *
     * @return the event ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the event.
     *
     * @param id the event ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the employee ID affected by the event.
     *
     * @return the employee ID.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID affected by the event.
     *
     * @param employeeId the employee ID to set.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the type of event (CREATE, UPDATE, DELETE).
     *
     * @return the event type.
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Sets the type of event (CREATE, UPDATE, DELETE).
     *
     * @param eventType the event type to set.
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the timestamp of when the event occurred.
     *
     * @return the event timestamp.
     */
    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    /**
     * Sets the timestamp of when the event occurred.
     *
     * @param eventTimestamp the event timestamp to set.
     */
    public void setEventTimestamp(Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    /**
     * Gets the IP address of the client who made the request.
     *
     * @return the client IP address.
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
     * Gets any additional details related to the event.
     *
     * @return the additional details of the event.
     */
    public String getAdditionalDetails() {
        return additionalDetails;
    }

    /**
     * Sets any additional details related to the event.
     *
     * @param additionalDetails the additional details to set.
     */
    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
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
        return "EventLog{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", eventType=" + eventType.name() +
                ", eventTimestamp=" + eventTimestamp +
                ", clientIp='" + clientIp + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                '}';
    }
}
