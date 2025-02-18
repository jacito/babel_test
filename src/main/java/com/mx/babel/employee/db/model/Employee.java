package com.mx.babel.employee.db.model;

import com.mx.babel.employee.db.model.enums.EmployeeStatus;
import com.mx.babel.employee.db.model.enums.Gender;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity class to represent the employees table.
 * This table stores information about employees.
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>Column: VARCHAR(100) NOT NULL</p>
     * First name of the employee.
     * This column stores the first name of the employee.
     */
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    /**
     * <p>Column: VARCHAR(100) NOT NULL</p>
     * Last name of the employee.
     * This column stores the last name of the employee.
     */
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /**
     * <p>Column: VARCHAR(100)</p>
     * Second last name of the employee (optional).
     * This column stores the second last name of the employee, if provided.
     */
    @Column(name = "second_last_name", length = 100)
    private String secondLastName;

    /**
     * <p>Column: INT NOT NULL</p>
     * Age of the employee.
     * This column stores the age of the employee.
     */
    @Column(name = "age", nullable = false)
    private int age;

    /**
     * <p>Column: ENUM('M', 'F', 'O') NOT NULL</p>
     * Gender of the employee (M = Male, F = Female, O = Other).
     * This column stores the gender of the employee.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    /**
     * <p>Column: DATE NOT NULL</p>
     * Birth date of the employee.
     * This column stores the birth date of the employee.
     */
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    /**
     * <p>Column: VARCHAR(100) NOT NULL</p>
     * Position or job title of the employee.
     * This column stores the position or job title of the employee.
     */
    @Column(name = "position", nullable = false, length = 100)
    private String position;

    /**
     * <p>Column: ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'</p>
     * Current status of the employee (ACTIVE or INACTIVE).
     * This column stores the current status of the employee.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'")
    private EmployeeStatus status;

    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on the + sign on the left to edit the code.">

    /**
     * Default constructor for creating a new instance of Employee.
     * This constructor does not initialize any fields.
     */
    public Employee() {
    }


    /**
     * Constructor to create an Employee object with all fields, including the ID.
     * This constructor is typically used when updating an existing employee,
     * where all fields (including ID) are required.
     *
     * @param id             the ID of the employee.
     * @param firstName      the first name of the employee.
     * @param lastName       the last name of the employee.
     * @param secondLastName the second last name of the employee (optional).
     * @param age            the age of the employee.
     * @param gender         the gender of the employee (M = Male, F = Female, O = Other).
     * @param birthDate      the birth date of the employee.
     * @param position       the position or job title of the employee.
     * @param status         the current status of the employee (ACTIVE or INACTIVE).
     *                       If null, defaults to ACTIVE.
     */
    public Employee(Long id, String firstName, String lastName, String secondLastName, int age, Gender gender, Date birthDate, String position, EmployeeStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.position = position;
        this.status = status != null ? status : EmployeeStatus.ACTIVE; // Default to ACTIVE if null
    }

    /**
     * Constructor to create an Employee object with essential fields.
     * This constructor is used when creating a new employee or updating an existing one,
     * where only the essential fields are provided (e.g., from EmployeeVO).
     *
     * @param firstName      the first name of the employee.
     * @param lastName       the last name of the employee.
     * @param secondLastName the second last name of the employee (optional).
     * @param age            the age of the employee.
     * @param gender         the gender of the employee (M = Male, F = Female, O = Other).
     * @param birthDate      the birth date of the employee.
     * @param position       the position or job title of the employee.
     * @param status         the current status of the employee (ACTIVE or INACTIVE).
     *                       If null, defaults to ACTIVE.
     */
    public Employee(String firstName, String lastName, String secondLastName, int age, Gender gender, Date birthDate, String position, EmployeeStatus status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.position = position;
        this.status = status != null ? status : EmployeeStatus.ACTIVE; // Default to ACTIVE if null
    }
    // </editor-fold>

    // Getters and Setters
    // <editor-fold defaultstate="collapsed" desc="Main getters and setters. Click on the + sign on the left to edit the code.">

    /**
     * Gets the unique identifier of the employee.
     *
     * @return the employee ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the employee.
     *
     * @param id the employee ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the employee.
     *
     * @return the first name of the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName the first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     *
     * @return the last name of the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the second last name of the employee.
     *
     * @return the second last name of the employee, or null if not provided.
     */
    public String getSecondLastName() {
        return secondLastName;
    }

    /**
     * Sets the second last name of the employee.
     *
     * @param secondLastName the second last name to set.
     */
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    /**
     * Gets the age of the employee.
     *
     * @return the age of the employee.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the employee.
     *
     * @param age the age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the gender of the employee.
     *
     * @return the gender of the employee.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the employee.
     *
     * @param gender the gender to set.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the birth date of the employee.
     *
     * @return the birth date of the employee.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the employee.
     *
     * @param birthDate the birth date to set.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the position or job title of the employee.
     *
     * @return the position or job title of the employee.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position or job title of the employee.
     *
     * @param position the position or job title to set.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the status of the employee.
     *
     * @return the status of the employee.
     */
    public EmployeeStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the employee.
     *
     * @param status the status to set.
     */
    public void setStatus(EmployeeStatus status) {
        this.status = status;
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
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", age=" + age +
                ", gender=" + gender.name() +
                ", birthDate=" + birthDate +
                ", position='" + position + '\'' +
                ", status=" + status.name() +
                '}';
    }
}
