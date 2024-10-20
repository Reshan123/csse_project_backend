package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReportScheduledNegativeTest {

    @Test
    public void testSetEmail_InvalidFormat() {
        ScheduledReport scheduledReport = new ScheduledReport();
        assertThrows(IllegalArgumentException.class, () -> {
            scheduledReport.setEmail("not-an-email");
        }, "Invalid email format.");
    }

    @Test
    public void testSetFrequency_InvalidValue() {
        ScheduledReport scheduledReport = new ScheduledReport();
        assertThrows(IllegalArgumentException.class, () -> {
            scheduledReport.setFrequency("every hour"); // Invalid frequency
        }, "Invalid frequency value.");
    }

    @Test
    public void testSetNextSendDate_PastDate() {
        ScheduledReport scheduledReport = new ScheduledReport();
        String pastDate = LocalDate.now().minusDays(1).toString(); // Convert to ISO date string
        assertThrows(IllegalArgumentException.class, () -> {
            scheduledReport.setNextSendDate(pastDate);
        }, "Next send date cannot be in the past.");
    }
}
