package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;
import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Repository.ScheduledReportRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Service.ScheduledReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportScheduleImplTest {

    @Mock
    private ScheduledReportRepository scheduledReportRepository;

    @InjectMocks
    private ScheduledReportServiceImpl scheduledReportService;

    private ScheduledReport testReport;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testReport = new ScheduledReport();
        testReport.setId("SR001");
        testReport.setEmail("test@example.com");
        testReport.setFrequency("daily");
        testReport.setNextSendDate("2024-10-21");
        testReport.setEmailSent(false);
    }

    @Test
    public void testCreateScheduledReport() {
        when(scheduledReportRepository.save(any(ScheduledReport.class))).thenReturn(testReport);

        ScheduledReport result = scheduledReportService.createScheduledReport(testReport);

        assertNotNull(result);
        assertEquals("SR001", result.getId());
        verify(scheduledReportRepository, times(1)).save(testReport);
    }

    @Test
    public void testGetScheduledReportById() {
        when(scheduledReportRepository.findById("SR001")).thenReturn(Optional.of(testReport));

        Optional<ScheduledReport> result = scheduledReportService.getScheduledReportById("SR001");

        assertTrue(result.isPresent());
        assertEquals("SR001", result.get().getId());
        verify(scheduledReportRepository, times(1)).findById("SR001");
    }

    @Test
    public void testGetScheduledReportById_NotFound() {
        when(scheduledReportRepository.findById("SR002")).thenReturn(Optional.empty());

        Optional<ScheduledReport> result = scheduledReportService.getScheduledReportById("SR002");

        assertFalse(result.isPresent());
        verify(scheduledReportRepository, times(1)).findById("SR002");
    }

    @Test
    public void testGetAllScheduledReports() {
        when(scheduledReportRepository.findAll()).thenReturn(Arrays.asList(testReport));

        var result = scheduledReportService.getAllScheduledReports();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(scheduledReportRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateScheduledReport() {
        when(scheduledReportRepository.findById("SR001")).thenReturn(Optional.of(testReport));
        when(scheduledReportRepository.save(any(ScheduledReport.class))).thenReturn(testReport);

        ScheduledReport updatedReport = new ScheduledReport();
        updatedReport.setEmail("updated@example.com");
        updatedReport.setFrequency("weekly");

        ScheduledReport result = scheduledReportService.updateScheduledReport("SR001", updatedReport);

        assertNotNull(result);
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("weekly", result.getFrequency());
        verify(scheduledReportRepository, times(1)).findById("SR001");
        verify(scheduledReportRepository, times(1)).save(any(ScheduledReport.class));
    }

    @Test
    public void testUpdateScheduledReport_NotFound() {
        when(scheduledReportRepository.findById("SR002")).thenReturn(Optional.empty());

        ScheduledReport updatedReport = new ScheduledReport();
        updatedReport.setEmail("updated@example.com");
        updatedReport.setFrequency("weekly");

        ScheduledReport result = scheduledReportService.updateScheduledReport("SR002", updatedReport);

        assertNull(result);
        verify(scheduledReportRepository, times(1)).findById("SR002");
        verify(scheduledReportRepository, times(0)).save(any(ScheduledReport.class));
    }

    @Test
    public void testEmailSent() {
        when(scheduledReportRepository.findById("SR001")).thenReturn(Optional.of(testReport));
        when(scheduledReportRepository.save(any(ScheduledReport.class))).thenReturn(testReport);

        ScheduledReport result = scheduledReportService.emailSent("SR001");

        assertNotNull(result);
        assertTrue(result.getEmailSent());
        verify(scheduledReportRepository, times(1)).findById("SR001");
        verify(scheduledReportRepository, times(1)).save(any(ScheduledReport.class));
    }

    @Test
    public void testDeleteScheduledReport() {
        scheduledReportService.deleteScheduledReport("SR001");

        verify(scheduledReportRepository, times(1)).deleteById("SR001");
    }
}
