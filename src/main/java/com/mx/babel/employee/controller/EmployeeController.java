package com.mx.babel.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.babel.employee.db.model.ErrorLog;
import com.mx.babel.employee.db.model.enums.EmployeeStatus;
import com.mx.babel.employee.db.model.EventLog;
import com.mx.babel.employee.db.model.enums.EventType;
import com.mx.babel.employee.exception.ErrorEmployee;
import com.mx.babel.employee.exception.Errors;
import com.mx.babel.employee.service.EmployeeService;
import com.mx.babel.employee.service.ErrorLogService;
import com.mx.babel.employee.service.EventLogService;
import com.mx.babel.employee.vo.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * EmployeeController class handles all the RESTful API endpoints related to Employee operations.
 */
@RestController
@RequestMapping("/api/employees")
@Api(tags = "Employee Management", description = "Operations related to employee management")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EventLogService eventLogService;
    @Autowired
    private ErrorLogService errorLogService;

    /**
     * API to retrieve the list of all active employees.
     *
     * @return List of active employees in JSON format.
     */
    @ApiOperation(value = "Get Active Employees",
            notes = "Fetches a list of employees that are currently active.",
            response = EmployeeVO.class,
            responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved active employees"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/active")
    public ResponseEntity<Object> getActiveEmployees(HttpServletRequest request) {
        logRequestHeaders(request);
        try {
            String clientIp = getClientIp(request);
            List<EmployeeVO> activeEmployees = employeeService.getEmployeesByStatus(EmployeeStatus.ACTIVE);
            eventLogService.createEvent(new EventLog(EventType.CONSULT_ACTIVE, clientIp));
            logger.info("Active Employees: {}", activeEmployees.size());
            return new ResponseEntity<>(activeEmployees, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(loadUnknownError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /**
     * API to retrieve the list of all registered employees.
     *
     * @param request HTTP request to extract client IP.
     * @return List of all employees in JSON format, or an error response.
     */
    @ApiOperation(
            value = "Get All Employees",
            notes = "Fetches a list of all employees in the system.",
            response = EmployeeVO.class,
            responseContainer = "List"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of employees"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Object> getAllEmployees(HttpServletRequest request) {
        logger.info("Get All Employees");
        logRequestHeaders(request);
        try {
            String clientIp = getClientIp(request);
            List<EmployeeVO> allEmployees = employeeService.getAllEmployeesAsVO();
            eventLogService.createEvent(new EventLog(EventType.CONSULT_ALL, clientIp));
            logger.info("All Employees: {}", allEmployees.size());
            return new ResponseEntity<>(allEmployees, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(loadUnknownError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * API to insert one or more employees.
     *
     * @param employees a list of employees to insert.
     * @return JSON with the status and the list of updated employees including the IDs.
     */
    @ApiOperation(value = "Add Employees",
            notes = "Adds one or more new employees.",
            response = EmployeeVO.class,
            responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Employees successfully added", response = EmployeeVO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Object> addEmployees(@RequestBody List<EmployeeVO> employees, HttpServletRequest request) {
        logger.info("Add Employees");
        logRequestHeaders(request);
        try {
            String clientIp = getClientIp(request);
            ObjectMapper objectMapper = new ObjectMapper();
            String employeesJson = objectMapper.writeValueAsString(employees);
            try {
                List<EmployeeVO> addedEmployees = employeeService.addEmployees(employees);
                eventLogService.createEvent(new EventLog(EventType.CREATE, clientIp, employeesJson));
                logger.info("Employees successfully added: {}", addedEmployees.size());
                return new ResponseEntity<>(addedEmployees, HttpStatus.CREATED);
            } catch (IllegalArgumentException ex) {
                errorLogService.createError(new ErrorLog(null, EventType.CREATE, ex.getMessage(),
                        null, null, clientIp, employeesJson, null));
                logger.error(ex.getMessage(), ex);
                return new ResponseEntity<>(new ErrorEmployee(ex.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(loadUnknownError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API to retrieve a specific employee by their ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return JSON representation of the employee, or an error if not found.
     */
    @ApiOperation(value = "Get Employee By ID",
            notes = "Fetches the details of a specific employee by their ID.",
            response = EmployeeVO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee found", response = EmployeeVO.class),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id, HttpServletRequest request) {
        logger.info("Get Employee with ID: {}", id);
        logRequestHeaders(request);
        try {
            String clientIp = request.getRemoteAddr();
            Optional<EmployeeVO> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                eventLogService.createEvent(new EventLog(id, EventType.CONSULT_BY_ID, clientIp));
                logger.info("Employee successfully found: {}", employee.get().getId());
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            } else {
                errorLogService.createError(new ErrorLog(id, EventType.CONSULT_BY_ID, Errors.EMPLOYEE_NOT_FOUND.getErrorMessage(),
                        null, null, clientIp, null, null));
                logger.warn("Employee not found with ID: {}", id);
                return new ResponseEntity<>(Errors.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(loadUnknownError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * API to delete an employee by their ID.
     *
     * @param id the ID of the employee to delete.
     * @return JSON indicating the success and the full name of the deleted employee.
     */
    @ApiOperation(value = "Delete Employee",
            notes = "Deletes an employee by their ID. Returns the deleted employee's details.",
            response = EmployeeVO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee successfully deleted", response = EmployeeVO.class),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id, HttpServletRequest request) {
        logger.info("Delete Employee with ID: {}", id);
        logRequestHeaders(request);
        try {
            String clientIp = getClientIp(request);
            EmployeeVO deletedEmployee = employeeService.deactivateById(id);
            if (deletedEmployee != null) {
                eventLogService.createEvent(new EventLog(id, EventType.DELETE, clientIp));
                logger.info("Employee successfully deleted: {}", deletedEmployee.getId());
                return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
            } else {
                errorLogService.createError(new ErrorLog(id, EventType.DELETE, Errors.EMPLOYEE_NOT_FOUND.getErrorMessage(),
                        null, null, clientIp, null, null));
                logger.warn("Employee not found with ID: {}", id);
                return new ResponseEntity<>(Errors.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(loadUnknownError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API to update an employee's data.
     *
     * @param id       the ID of the employee to update.
     * @param employee the updated employee data.
     * @return JSON with the updated employee information.
     */
    @ApiOperation(value = "Update Employee",
            notes = "Updates an employee's information by their ID.",
            response = EmployeeVO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Employee successfully updated", response = EmployeeVO.class),
            @ApiResponse(code = 404, message = "Employee not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody EmployeeVO employee, HttpServletRequest request) {
        logger.info("Update Employee with ID: {}", id);
        logRequestHeaders(request);
        try {
            String clientIp = getClientIp(request);
            ObjectMapper objectMapper = new ObjectMapper();
            String employeesJson = objectMapper.writeValueAsString(employee);

            try {

                Optional<EmployeeVO> updatedEmployee = employeeService.updateEmployee(id, employee);
                if (updatedEmployee.isPresent()) {
                    eventLogService.createEvent(new EventLog(id, EventType.UPDATE, clientIp, employeesJson));
                    logger.info("Employee successfully updated: {}", updatedEmployee.get().getId());
                    return new ResponseEntity<>(updatedEmployee.get(), HttpStatus.OK);
                } else {
                    errorLogService.createError(new ErrorLog(id, EventType.UPDATE, Errors.EMPLOYEE_NOT_FOUND.getErrorMessage(),
                            null, null, clientIp, employeesJson, null));
                    logger.warn("Employee not found with ID: {}", id);
                    return new ResponseEntity<>(Errors.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND);
                }
            } catch (IllegalArgumentException ex) {
                errorLogService.createError(new ErrorLog(null, EventType.UPDATE, ex.getMessage(),
                        null, null, clientIp, employeesJson, null));
                logger.error(ex.getMessage(), ex);
                return new ResponseEntity<>(new ErrorEmployee(ex.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(loadUnknownError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves the client's IP address from the request.
     * If the request goes through a proxy or load balancer, it takes the value from the "X-Forwarded-For" header.
     * If this header is not available, it uses the direct IP address obtained via the `getRemoteAddr()` method.
     *
     * @param request The `HttpServletRequest` object containing the HTTP request information.
     * @return The client's IP address as a string.
     */
    private String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        logger.trace("getClientIp: {}", clientIp);
        return clientIp;
    }

    /**
     * Loads or updates the error message for an unknown error.
     * <p>
     * This method retrieves a predefined {@link ErrorEmployee} instance with an unknown error message
     * and the current timestamp when invoked. It is used to handle unexpected errors that do not fall under
     * predefined categories.
     *
     * @param ex the exception that caused the error
     * @return the {@link ErrorEmployee} object containing the error details.
     */
    private ErrorEmployee loadUnknownError(Exception ex) {
        return new ErrorEmployee(Errors.UNKNOWN.getErrorMessage() + " :: " + ex.getMessage() + " " + LocalDateTime.now());
    }


    /**
     * Prints all headers of the HTTP request to the log.
     * This method iterates over the headers and logs each key-value pair.
     *
     * @param request The `HttpServletRequest` object containing the HTTP request information.
     */
    private void logRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                Enumeration<String> headerValues = request.getHeaders(headerName);

                StringBuilder headerValueBuilder = new StringBuilder();
                while (headerValues.hasMoreElements()) {
                    headerValueBuilder.append(headerValues.nextElement());
                    if (headerValues.hasMoreElements()) {
                        headerValueBuilder.append(", ");
                    }
                }

                logger.debug("Header: {} = {}", headerName, headerValueBuilder.toString());
            }
            logger.debug("Headers :: NULL");
        }
    }

}
