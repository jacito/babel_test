package com.mx.babel.employee.service;

import com.mx.babel.employee.db.model.ErrorLog;
import com.mx.babel.employee.db.model.enums.EventType;
import com.mx.babel.employee.db.repository.ErrorLogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ErrorLogServiceTest {

    @Mock
    private ErrorLogRepository errorLogRepository;

    @InjectMocks
    private ErrorLogService errorLogService;

    @Test
    public void createError() {
        Long employeeId = 1L;
        ErrorLog errorLog = new ErrorLog(employeeId, EventType.CREATE, "Error :: Created new employee",
                null, null, null, null, null);

        when(errorLogRepository.save(errorLog)).thenReturn(errorLog);

        ErrorLog createdEvent = errorLogService.createError(errorLog);

        assertNotNull(createdEvent);
        assertEquals("CREATE", createdEvent.getEventType().toString());
        assertEquals(employeeId, createdEvent.getEmployeeId());
    }

    @Test
    public void getAllErrorLogs() {

        ErrorLog errorLog1 = new ErrorLog(1L, EventType.CREATE, "Error :: Created new employeeid [" + 1L + "]",
                null, null, null, null, null);
        ErrorLog errorLog2 = new ErrorLog(2L, EventType.UPDATE, "Error :: Update employee id [" + 2L + "]",
                null, null, null, null, null);
        ErrorLog errorLog3 = new ErrorLog(3L, EventType.DELETE, "Error :: Delete employee id [" + 3L + "]",
                null, null, null, null, null);

        when(errorLogRepository.save(errorLog1)).thenReturn(errorLog1);
        when(errorLogRepository.save(errorLog2)).thenReturn(errorLog2);
        when(errorLogRepository.save(errorLog3)).thenReturn(errorLog3);

        List<ErrorLog> mockErrorLogs = Arrays.asList(errorLog1, errorLog2, errorLog3);
        when(errorLogRepository.findAll()).thenReturn(mockErrorLogs);

        List<ErrorLog> ErrorLogs = errorLogService.getAllErrorLogs();

        assertNotNull(ErrorLogs);
        assertEquals(3, ErrorLogs.size());
        assertFalse(ErrorLogs.isEmpty());
        for (ErrorLog ErrorLog : ErrorLogs) {
            System.out.println(ErrorLogs);
        }
    }
}