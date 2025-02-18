package com.mx.babel.employee.db.repository;

import com.mx.babel.employee.db.model.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing the event_log table.
 * Provides methods to interact with the event_log table for basic operations: insert, get by employee ID, and get all.
 */
@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    /**
     * Inserts a new event log into the database.
     * This is handled by JpaRepository automatically, as it provides basic CRUD operations.
     *
     * @param eventLog the event log to insert.
     * @return the saved event log.
     */
    EventLog save(EventLog eventLog);

    /**
     * Finds all event logs for a specific employee by their ID.
     *
     * @param employeeId the ID of the employee to retrieve event logs for.
     * @return a list of event logs related to the specified employee.
     */
    List<EventLog> findByEmployeeId(Long employeeId);

    /**
     * Finds all event logs in the system.
     *
     * @return a list of all event logs.
     */
    List<EventLog> findAll();
}
