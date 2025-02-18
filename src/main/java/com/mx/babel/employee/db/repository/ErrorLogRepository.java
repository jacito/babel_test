package com.mx.babel.employee.db.repository;

import com.mx.babel.employee.db.model.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing the error_log table.
 * Provides methods to interact with the error_log table for basic operations: insert and get all.
 */
@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {

    /**
     * Inserts a new error log into the database.
     * This is handled by JpaRepository automatically, as it provides basic CRUD operations.
     *
     * @param errorLog the error log to insert.
     * @return the saved error log.
     */
    ErrorLog save(ErrorLog errorLog);

    /**
     * Finds all error logs in the system.
     *
     * @return a list of all error logs.
     */
    List<ErrorLog> findAll();
}
