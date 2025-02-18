package com.mx.babel.employee.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Represents the data of an employee in a simplified form, used for business logic or API responses.
 * This is a Value Object (VO) that contains the relevant fields of an employee without direct database mapping.
 */
public class EmployeeVO {

    /**
     * ID of the employee.
     */
    private Long id;

    /**
     * The first name of the employee.
     */
    private String firstName;

    /**
     * The last name of the employee.
     */
    private String lastName;

    /**
     * The second last name of the employee.
     */
    private String secondLastName;

    /**
     * The age of the employee.
     */
    private Integer age;

    /**
     * The gender of the employee (e.g., male, female, non-binary).
     */
    private String gender;

    /**
     * The birth date of the employee.
     */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    /**
     * The position or job title of the employee within the organization.
     */
    private String position;

    /**
     * The status of the employee (e.g., active, inactive).
     */
    private String status;

    /**
     * The full name of the employee, which combines the first, last, and second last names.
     * This field is computed dynamically by concatenating the first name, last name, and second last name.
     */
    private String fullName;

    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on the + sign on the left to edit the code.">

    /**
     * Default constructor for creating a new instance of Employee.
     * This constructor does not initialize any fields.
     */
    public EmployeeVO() {
    }

    /**
     * Constructor for the EmployeeVO. Initializes all fields including the full name.
     *
     * @param id             The ID of tje employee.
     * @param firstName      The first name of the employee.
     * @param lastName       The last name of the employee.
     * @param secondLastName The second last name of the employee.
     * @param age            The age of the employee.
     * @param gender         The gender of the employee.
     * @param birthDate      The birth date of the employee.
     * @param position       The position of the employee.
     * @param status         The status of the employee.
     */
    public EmployeeVO(Long id, String firstName, String lastName, String secondLastName, Integer age,
                      String gender, Date birthDate, String position, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.position = position;
        this.status = status;
        this.fullName = firstName + " " + lastName + " " + secondLastName;  // Concatenates names for the full name
    }

    /**
     * Constructor for the EmployeeVO.
     *
     * @param firstName      The first name of the employee.
     * @param lastName       The last name of the employee.
     * @param secondLastName The second last name of the employee.
     * @param age            The age of the employee.
     * @param gender         The gender of the employee.
     * @param birthDate      The birth date of the employee.
     * @param position       The position of the employee.
     * @param status         The status of the employee.
     */
    public EmployeeVO(String firstName, String lastName, String secondLastName, Integer age,
                      String gender, Date birthDate, String position, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.age = age;
        this.gender = gender;
        this.birthDate = birthDate;
        this.position = position;
        this.status = status;
        this.fullName = firstName + " " + lastName + " " + secondLastName;
    }

    /**
     * Constructor for EmployeeVO.
     * If onlyFullName is true, only the fullName will be set.
     * Otherwise, all fields are initialized based on the employee data.
     *
     * @param id             The ID of tje employee.
     * @param firstName      The first name of the employee.
     * @param lastName       The last name of the employee.
     * @param secondLastName The second last name of the employee.
     * @param onlyFullName   a boolean indicating if only the full name should be populated
     */
    public EmployeeVO(Long id, String firstName, String lastName, String secondLastName, boolean onlyFullName) {
        this.id = id;
        if (!onlyFullName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.secondLastName = secondLastName;
        }
        this.fullName = firstName + " " + lastName + " " + secondLastName;
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
     * Retrieves the full name of the employee, which is a combination of the first, last, and second last names.
     *
     * @return the full name of the employee.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Retrieves the first name of the employee.
     *
     * @return the first name of the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName the first name of the employee.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the employee.
     *
     * @return the last name of the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the last name of the employee.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the second last name of the employee.
     *
     * @return the second last name of the employee.
     */
    public String getSecondLastName() {
        return secondLastName;
    }

    /**
     * Sets the second last name of the employee.
     *
     * @param secondLastName the second last name of the employee.
     */
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    /**
     * Retrieves the age of the employee.
     *
     * @return the age of the employee.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age of the employee.
     *
     * @param age the age of the employee.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Retrieves the gender of the employee.
     *
     * @return the gender of the employee.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the employee.
     *
     * @param gender the gender of the employee.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the birth date of the employee.
     *
     * @return the birth date of the employee.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the employee.
     *
     * @param birthDate the birth date of the employee.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves the position or job title of the employee.
     *
     * @return the position of the employee.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of the employee.
     *
     * @param position the position of the employee.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Retrieves the status of the employee.
     *
     * @return the status of the employee.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the employee.
     *
     * @param status the status of the employee.
     */
    public void setStatus(String status) {
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
        StringBuilder sb = new StringBuilder("EmployeeVO{");

        if (id != null) {
            sb.append("id=").append(id).append(", ");
        }
        if (firstName != null) {
            sb.append("firstName='").append(firstName).append("', ");
        }
        if (lastName != null) {
            sb.append("lastName='").append(lastName).append("', ");
        }
        if (secondLastName != null) {
            sb.append("secondLastName='").append(secondLastName).append("', ");
        }
        if (age != null) {
            sb.append("age=").append(age).append(", ");
        }
        if (gender != null) {
            sb.append("gender='").append(gender).append("', ");
        }
        if (birthDate != null) {
            sb.append("birthDate=").append(birthDate).append(", ");
        }
        if (position != null) {
            sb.append("position='").append(position).append("', ");
        }
        if (status != null) {
            sb.append("status='").append(status).append("', ");
        }
        if (fullName != null) {
            sb.append("fullName='").append(fullName).append("', ");
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.setLength(sb.length() - 2);
        }

        sb.append('}');
        return sb.toString();
    }

}

