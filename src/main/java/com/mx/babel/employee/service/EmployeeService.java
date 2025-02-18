package com.mx.babel.employee.service;

import com.mx.babel.employee.db.model.Employee;
import com.mx.babel.employee.db.model.enums.EmployeeStatus;
import com.mx.babel.employee.db.repository.EmployeeRepository;
import com.mx.babel.employee.exception.EmployeeNotFoundException;
import com.mx.babel.employee.exception.UnknownErrorException;
import com.mx.babel.employee.vo.EmployeeVO;
import com.mx.babel.employee.db.model.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for Employee entity.
 * Contains business logic for managing employees.
 */
@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);


    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieves all employees with the specified status (active, inactive, etc.).
     *
     * @param status the status to filter employees by (e.g., "active", "inactive").
     * @return a list of EmployeeVOs with the specified status.
     */
    public List<EmployeeVO> getEmployeesByStatus(EmployeeStatus status) {
        logger.info("getEmployeesByStatus {}", status.name());
        try {
            List<Employee> employees = employeeRepository.findByStatus(status);
            return employees.stream()
                    .map(this::convertToEmployeeVO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new UnknownErrorException(ex);
        }
    }

    /**
     * Retrieves an employee by its ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return an Optional containing the EmployeeVO if found, otherwise empty.
     */
    public Optional<EmployeeVO> getEmployeeById(Long id) {
        logger.debug("getEmployeeById {}", id);
        try {
            Optional<Employee> employeeOpt = employeeRepository.findById(id);
            return employeeOpt.map(this::convertToEmployeeVO);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new UnknownErrorException(ex);
        }
    }

    /**
     * Marks an employee as inactive based on its ID. (Soft delete operation)
     *
     * @param id the ID of the employee to deactivate.
     * @return the EmployeeVO containing the id and full name if the employee was found and deactivated,
     * or null if the employee was not found.
     */
    public EmployeeVO deactivateById(Long id) {
        logger.debug("deactivateById {}", id);
        try {
            Optional<Employee> employeeOpt = employeeRepository.findById(id);
            if (employeeOpt.isPresent()) {
                Employee employee = employeeOpt.get();
                employee.setStatus(EmployeeStatus.INACTIVE);
                employeeRepository.save(employee);

                EmployeeVO employeeVO = new EmployeeVO(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSecondLastName(),
                        true
                );
                return employeeVO;
            } else {
                throw new EmployeeNotFoundException("Employee [" + id + "] not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new UnknownErrorException(ex);
        }
    }


    /**
     * Updates the details of an employee based on its ID.
     * Only non-null fields will be updated.
     *
     * @param id              the ID of the employee to update.
     * @param updatedEmployee the updated employee data (EmployeeVO).
     * @return the updated EmployeeVO if successful, or empty if not found.
     */
    public Optional<EmployeeVO> updateEmployee(Long id, EmployeeVO updatedEmployee) {
       logger.debug("updateEmployee {}", id);
        try {
            Optional<Employee> existingEmployeeOpt = employeeRepository.findById(id);

            if (existingEmployeeOpt.isPresent()) {
                Employee existingEmployee = existingEmployeeOpt.get();

                // Update fields only if they are non-null
                if (updatedEmployee.getFirstName() != null) {
                    existingEmployee.setFirstName(updatedEmployee.getFirstName());
                }
                if (updatedEmployee.getLastName() != null) {
                    existingEmployee.setLastName(updatedEmployee.getLastName());
                }
                if (updatedEmployee.getSecondLastName() != null) {
                    existingEmployee.setSecondLastName(updatedEmployee.getSecondLastName());
                }
                if (updatedEmployee.getAge() != null && updatedEmployee.getAge() > 0) {
                    existingEmployee.setAge(updatedEmployee.getAge());
                }
                if (updatedEmployee.getGender() != null) {
                    // Convert String gender to Enum
                    try {
                        existingEmployee.setGender(Gender.valueOf(updatedEmployee.getGender().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        // Handle the case where the gender is invalid
                        throw new IllegalArgumentException("The gender '" + updatedEmployee.getGender().toUpperCase() + "' does not match any of the valid options in the Gender enum: " +
                                String.join(", ", Gender.values().toString()));
                    }
                }
                if (updatedEmployee.getBirthDate() != null) {
                    existingEmployee.setBirthDate(updatedEmployee.getBirthDate());
                }
                if (updatedEmployee.getPosition() != null) {
                    existingEmployee.setPosition(updatedEmployee.getPosition());
                }

                employeeRepository.save(existingEmployee);

                EmployeeVO employeeVO = new EmployeeVO(
                        existingEmployee.getId(),
                        existingEmployee.getFirstName(),
                        existingEmployee.getLastName(),
                        existingEmployee.getSecondLastName(),
                        true
                );
                return Optional.of(employeeVO);
            } else {
                throw new EmployeeNotFoundException("Employee [" + id + "] not found");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new UnknownErrorException(ex);
        }
    }


    /**
     * Inserts one or more employees into the database.
     *
     * @param employees a list of EmployeeVOs to insert.
     * @return the list of EmployeeVOs representing the inserted employees.
     */
    public List<EmployeeVO> addEmployees(List<EmployeeVO> employees) {
        logger.debug("addEmployees {}", employees);
        try {
            List<Employee> employeesToSave = employees.stream()
                    .map(employeeVO -> new Employee(
                            employeeVO.getFirstName(),
                            employeeVO.getLastName(),
                            employeeVO.getSecondLastName(),
                            employeeVO.getAge(),
                            Gender.valueOf(employeeVO.getGender().toUpperCase()), // Convert String gender to Enum
                            employeeVO.getBirthDate(),
                            employeeVO.getPosition(),
                            EmployeeStatus.ACTIVE // Assuming new employees are active by default
                    ))
                    .collect(Collectors.toList());

            List<Employee> savedEmployees = employeeRepository.saveAll(employeesToSave);

            return savedEmployees.stream()
                    .map(this::convertToEmployeeVO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new UnknownErrorException(ex);
        }
    }


    /**
     * Retrieves all employees (active, inactive, or any other status) as EmployeeVOs.
     *
     * @return a list of EmployeeVOs.
     */
    public List<EmployeeVO> getAllEmployeesAsVO() {
        logger.debug("getAllEmployeesAsVO");
        try {
            List<Employee> employees = employeeRepository.findAll();
            return employees.stream()
                    .map(this::convertToEmployeeVO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new UnknownErrorException(ex);
        }
    }

    /**
     * Converts an Employee entity to an EmployeeVO.
     *
     * @param employee the Employee entity to convert.
     * @return the EmployeeVO representation of the employee.
     */
    public EmployeeVO convertToEmployeeVO(Employee employee) {
        logger.trace(String.valueOf(employee));
        return new EmployeeVO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSecondLastName(),
                employee.getAge(),
                employee.getGender().name(),
                employee.getBirthDate(),
                employee.getPosition(),
                employee.getStatus().name()
        );
    }
}
