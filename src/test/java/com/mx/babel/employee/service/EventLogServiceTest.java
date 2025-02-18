package com.mx.babel.employee.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.mx.babel.employee.db.model.enums.EventType;
import com.mx.babel.employee.db.repository.EventLogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.babel.employee.db.model.EventLog;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EventLogServiceTest {


    @Mock
    private EventLogRepository eventLogRepository;

    @InjectMocks
    private EventLogService eventLogService;


    @Test
    public void createEvent() {
        Long employeeId = 1L;
        EventLog eventLog = new EventLog(employeeId, EventType.CREATE, "Created new employee");

        when(eventLogRepository.save(eventLog)).thenReturn(eventLog);

        EventLog createdEvent = eventLogService.createEvent(eventLog);

        assertNotNull(createdEvent);
        assertEquals("CREATE", createdEvent.getEventType().toString());
        assertEquals(employeeId, createdEvent.getEmployeeId());
    }

    @Test
    public void getEventLogsByEmployeeId() {
        Long employeeId = 1L;
        EventLog eventLog1 = new EventLog(employeeId, EventType.CREATE, "Created new employee");
        EventLog eventLog2 = new EventLog(employeeId, EventType.UPDATE, "Update employee id [" + employeeId + "]");

        when(eventLogRepository.save(eventLog1)).thenReturn(eventLog1);
        when(eventLogRepository.save(eventLog2)).thenReturn(eventLog2);

        List<EventLog> mockEventLogs = Arrays.asList(eventLog1, eventLog2);
        when(eventLogRepository.findByEmployeeId(employeeId)).thenReturn(mockEventLogs);

        List<EventLog> eventLogs = eventLogService.getEventLogsByEmployeeId(employeeId);

        assertNotNull(eventLogs);
        assertEquals(2, eventLogs.size());
        assertFalse(eventLogs.isEmpty());
        for (EventLog eventLog : eventLogs) {
            System.out.println(eventLogs);
        }
    }


    @Test
    public void getAllEventLogs() {
        Long employeeId1 = 1L;
        Long employeeId2 = 1L;

        EventLog eventLog11 = new EventLog(employeeId1, EventType.CREATE, "Created new employeeid [" + employeeId1 + "]");
        EventLog eventLog21 = new EventLog(employeeId1, EventType.UPDATE, "Update employee id [" + employeeId1 + "]");

        EventLog eventLog12 = new EventLog(employeeId1, EventType.CREATE, "Created new employeeid [" + employeeId2 + "]");
        EventLog eventLog22 = new EventLog(employeeId1, EventType.UPDATE, "Update employee id [" + employeeId2 + "]");
        EventLog eventLog32 = new EventLog(employeeId1, EventType.DELETE, "Delete employee id [" + employeeId2 + "]");

        when(eventLogRepository.save(eventLog11)).thenReturn(eventLog11);
        when(eventLogRepository.save(eventLog21)).thenReturn(eventLog21);

        when(eventLogRepository.save(eventLog12)).thenReturn(eventLog12);
        when(eventLogRepository.save(eventLog22)).thenReturn(eventLog22);
        when(eventLogRepository.save(eventLog32)).thenReturn(eventLog32);

        List<EventLog> mockEventLogs = Arrays.asList(eventLog11, eventLog21, eventLog12, eventLog22, eventLog32);
        when(eventLogRepository.findAll()).thenReturn(mockEventLogs);

        List<EventLog> eventLogs = eventLogService.getAllEventLogs();

        assertNotNull(eventLogs);
        assertEquals(5, eventLogs.size());
        assertFalse(eventLogs.isEmpty());
        for (EventLog eventLog : eventLogs) {
            System.out.println(eventLogs);
        }
    }
}
