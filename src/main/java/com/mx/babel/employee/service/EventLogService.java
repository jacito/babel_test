package com.mx.babel.employee.service;

import com.mx.babel.employee.db.model.EventLog;
import com.mx.babel.employee.db.repository.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class to handle business logic for event logs.
 * Provides methods for inserting, retrieving event logs by employee ID, and retrieving all event logs.
 */
@Service
public class EventLogService {

    private static final Logger logger = LoggerFactory.getLogger(EventLogService.class);


    private final EventLogRepository eventLogRepository;

    /**
     * Constructor to inject the EventLogRepository dependency.
     *
     * @param eventLogRepository the EventLog repository to be injected.
     */
    @Autowired
    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    /**
     * Creates a new event log in the database.
     * <p>
     * This method saves the event log provided to the database.
     *
     * @param eventLog the event log to be created.
     * @return the created event log.
     */
    public EventLog createEvent(EventLog eventLog) {
        logger.info("Create a new event log {}", eventLog);
        return eventLogRepository.save(eventLog);
    }

    /**
     * Retrieves all event logs for a specific employee based on their employee ID.
     * <p>
     * This method fetches event logs associated with the provided employee ID.
     *
     * @param employeeId the ID of the employee to fetch event logs for.
     * @return a list of event logs related to the specified employee.
     */
    public List<EventLog> getEventLogsByEmployeeId(Long employeeId) {
        logger.debug("getEventLogsByEmployeeId - employeeId: {}", employeeId);
        return eventLogRepository.findByEmployeeId(employeeId);
    }

    /**
     * Retrieves all event logs stored in the system.
     * <p>
     * This method fetches all event logs in the database.
     *
     * @return a list of all event logs.
     */
    public List<EventLog> getAllEventLogs() {
        logger.debug("getAllEventLogs");
        return eventLogRepository.findAll();
    }
}
