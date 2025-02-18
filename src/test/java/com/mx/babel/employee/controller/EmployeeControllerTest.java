package com.mx.babel.employee.controller;

import com.mx.babel.employee.db.model.Employee;
import com.mx.babel.employee.db.model.enums.EmployeeStatus;
import com.mx.babel.employee.db.model.enums.Gender;
import com.mx.babel.employee.service.EmployeeService;
import com.mx.babel.employee.service.ErrorLogService;
import com.mx.babel.employee.service.EventLogService;
import com.mx.babel.employee.vo.EmployeeVO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Mock
    private EventLogService eventLogService;

    @Mock
    private ErrorLogService errorLogService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    HttpServletRequest request;
    private Employee employee;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() throws ParseException {
        MockitoAnnotations.initMocks(this);
        request = Mockito.mock(HttpServletRequest.class);
        employee = new Employee(
                "John", "Doe", "Smith", 30, Gender.M,
                format.parse("1991-01-01"), "Developer", EmployeeStatus.ACTIVE);
        employee.setId(1L);
    }

    @Test
    public void getActiveEmployees() {
        EmployeeVO employeeVO = convertToEmployeeVO(employee);
        when(employeeService.getEmployeesByStatus(EmployeeStatus.ACTIVE)).thenReturn(Arrays.asList(employeeVO));

        ResponseEntity<Object> result = employeeController.getActiveEmployees(request);

        assertNotNull(result);

        assertEquals(1, ((List<EmployeeVO>) result.getBody()).size());

        EmployeeVO returnedEmployee = ((List<EmployeeVO>) result.getBody()).get(0);

        assertEquals("John Doe Smith", returnedEmployee.getFullName());
        System.out.println(returnedEmployee);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void getAllEmployees() {
        EmployeeVO employeeVO = convertToEmployeeVO(employee);
        when(employeeService.getAllEmployeesAsVO()).thenReturn(Arrays.asList(employeeVO));

        ResponseEntity<Object> result = employeeController.getAllEmployees(request);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void deleteEmployee() {
        EmployeeVO employeeVO = new EmployeeVO(employee.getId(), employee.getFirstName(),
                employee.getLastName(), employee.getSecondLastName(), true);
        when(employeeService.deactivateById(Mockito.anyLong())).thenReturn(employeeVO);

        ResponseEntity<Object> result = employeeController.deleteEmployee(1L, request);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateEmployee() {
        EmployeeVO employeeVO = new EmployeeVO(employee.getId(), employee.getFirstName(),
                employee.getLastName(), employee.getSecondLastName(), true);
        when(employeeService.updateEmployee(1L, employeeVO)).thenReturn(Optional.of(employeeVO));

        ResponseEntity<Object> result = employeeController.updateEmployee(1L, employeeVO, request);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void addEmployees() {
        EmployeeVO employeeVO = convertToEmployeeVO(employee);
        when(employeeService.addEmployees(Arrays.asList(employeeVO))).thenReturn(Arrays.asList(employeeVO));
        ResponseEntity<Object> result = employeeController.addEmployees(Arrays.asList(employeeVO), request);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void getEmployeeById() {
        EmployeeVO employeeVO = convertToEmployeeVO(employee);
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employeeVO));

        ResponseEntity<Object> result = employeeController.getEmployeeById(1L, request);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    private EmployeeVO convertToEmployeeVO(Employee employee) {
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
