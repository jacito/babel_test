package com.mx.babel.employee.db.repository;

import com.mx.babel.employee.db.model.Employee;
import com.mx.babel.employee.db.model.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Employee entity.
 * Provides methods to perform CRUD operations on Employee data.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Retrieves all employees with the specified status.
     *
     * @param status the status to filter employees by (e.g., "active", "inactive").
     * @return a list of employees with the specified status.
     */
    List<Employee> findByStatus(EmployeeStatus status);

    /**
     * Retrieves an employee by its ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return an Optional containing the employee if found, otherwise empty.
     */
    Optional<Employee> findById(Long id);

    /**
     * Retrieves all employees (active or inactive).
     *
     * @return a list of all employees.
     */
    List<Employee> findAll();





}
