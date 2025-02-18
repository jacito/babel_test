package com.mx.babel.employee.service;

import com.mx.babel.employee.db.model.Employee;
import com.mx.babel.employee.db.model.enums.EmployeeStatus;
import com.mx.babel.employee.db.model.enums.Gender;
import com.mx.babel.employee.db.repository.EmployeeRepository;
import com.mx.babel.employee.vo.EmployeeVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() throws ParseException {
        employee = new Employee(
                "John", "Doe", "Smith", 30, Gender.M,
                format.parse("1991-01-01"), "Developer", EmployeeStatus.ACTIVE);
        employee.setId(1L);
    }

    @Test
    public void getEmployeesByStatus() {
        EmployeeStatus status = EmployeeStatus.ACTIVE;
        when(employeeRepository.findByStatus(status)).thenReturn(Arrays.asList(employee));

        List<EmployeeVO> employees = employeeService.getEmployeesByStatus(status);

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("John", employees.get(0).getFirstName());

        System.out.println(employees.get(0));
        verify(employeeRepository).findByStatus(status);
    }

    @Test
    public void getEmployeeById() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Optional<EmployeeVO> result = employeeService.getEmployeeById(employeeId);

        assertTrue(result.isPresent());
        assertEquals(employeeId, result.get().getId());

        System.out.println(result.get());

        verify(employeeRepository).findById(employeeId);
    }

    @Test
    public void deactivateById() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        EmployeeVO result = employeeService.deactivateById(employeeId);

        assertEquals(employeeId, result.getId());

        System.out.println(result);

        verify(employeeRepository).findById(employeeId);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void updateEmployee() throws ParseException {
        Long employeeId = 1L;
        EmployeeVO updatedEmployeeVO = new EmployeeVO(
                "John", "Doe", "Johnson", 32, null,
                format.parse("1991-10-01"), "Senior Developer", "ACTIVE"
        );

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Optional<EmployeeVO> result = employeeService.updateEmployee(employeeId, updatedEmployeeVO);

        assertTrue(result.isPresent());
        assertEquals(employeeId, result.get().getId());

        System.out.println(result.get());

        verify(employeeRepository).findById(employeeId);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    public void addEmployees() throws ParseException {
        EmployeeVO newEmployeeVO = employeeService.convertToEmployeeVO(employee);

        when(employeeRepository.saveAll(anyList())).thenReturn(Arrays.asList(employee));

        List<EmployeeVO> addedEmployees = employeeService.addEmployees(Arrays.asList(newEmployeeVO));

        assertNotNull(addedEmployees);
        assertEquals(1, addedEmployees.size());
        assertEquals("John", addedEmployees.get(0).getFirstName());

        System.out.println(addedEmployees);

        verify(employeeRepository).saveAll(anyList());
    }

    @Test
    public void getAllEmployeesAsVO() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        List<EmployeeVO> allEmployees = employeeService.getAllEmployeesAsVO();

        assertNotNull(allEmployees);
        assertEquals(1, allEmployees.size());
        assertEquals("John", allEmployees.get(0).getFirstName());

        System.out.println(allEmployees);

        verify(employeeRepository).findAll();
    }
}