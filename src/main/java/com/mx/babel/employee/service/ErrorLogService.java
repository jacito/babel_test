package com.mx.babel.employee.service;

import com.mx.babel.employee.db.model.ErrorLog;
import com.mx.babel.employee.db.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class to handle business logic for error logs.
 * Provides methods for inserting error logs and retrieving all error logs.
 */
@Service
public class ErrorLogService {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLogService.class);

    private final ErrorLogRepository errorLogRepository;

    /**
     * Constructor to inject the ErrorLogRepository dependency.
     *
     * @param errorLogRepository the ErrorLog repository to be injected.
     */
    @Autowired
    public ErrorLogService(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    /**
     * Creates a new error log in the database.
     * <p>
     * This method saves the error log provided to the database.
     *
     * @param errorLog the error log to be created.
     * @return the created error log.
     */
    public ErrorLog createError(ErrorLog errorLog) {
        logger.debug("Creating error log: {}", errorLog);
        return errorLogRepository.save(errorLog);
    }

    /**
     * Retrieves all error logs stored in the system.
     * <p>
     * This method fetches all error logs in the database.
     *
     * @return a list of all error logs.
     */
    public List<ErrorLog> getAllErrorLogs() {
        logger.debug("Getting all error logs");
        return errorLogRepository.findAll();
    }
}
