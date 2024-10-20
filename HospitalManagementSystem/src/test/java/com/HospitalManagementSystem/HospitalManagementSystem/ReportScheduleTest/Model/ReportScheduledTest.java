package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportScheduledTest {

    @Test
    public void testScheduledReportConstructorAndGetters() {
        String id = "SR001";
        String email = "test@example.com";
        String frequency = "daily";
        String nextSendDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_DATE);
        Boolean emailSent = false;

        ScheduledReport scheduledReport = new ScheduledReport();
        scheduledReport.setId(id);
        scheduledReport.setEmail(email);
        scheduledReport.setFrequency(frequency);
        scheduledReport.setNextSendDate(nextSendDate);
        scheduledReport.setEmailSent(emailSent);

        assertEquals(id, scheduledReport.getId());
        assertEquals(email, scheduledReport.getEmail());
        assertEquals(frequency, scheduledReport.getFrequency());
        assertEquals(nextSendDate, scheduledReport.getNextSendDate());
        assertEquals(emailSent, scheduledReport.getEmailSent());
    }

    @Test
    public void testScheduledReportSetters() {
        ScheduledReport scheduledReport = new ScheduledReport();
        String id = "SR001";
        String email = "test@example.com";
        String frequency = "daily";
        String nextSendDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_DATE);
        Boolean emailSent = false;

        scheduledReport.setId(id);
        scheduledReport.setEmail(email);
        scheduledReport.setFrequency(frequency);
        scheduledReport.setNextSendDate(nextSendDate);
        scheduledReport.setEmailSent(emailSent);

        assertEquals(id, scheduledReport.getId());
        assertEquals(email, scheduledReport.getEmail());
        assertEquals(frequency, scheduledReport.getFrequency());
        assertEquals(nextSendDate, scheduledReport.getNextSendDate());
        assertEquals(emailSent, scheduledReport.getEmailSent());
    }
}
