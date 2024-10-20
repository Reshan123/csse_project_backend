package com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Document(collection = "scheduledReports")
@Data
public class ScheduledReport {

    @Id
    private String id;
    private String email;
    private String frequency;
    private String nextSendDate;

    // Constructor with default nextSendDate
    public ScheduledReport() {
        // Automatically set nextSendDate to one week from today
        this.nextSendDate = LocalDate.now().plusWeeks(1).format(DateTimeFormatter.ISO_DATE);
    }

    // Additional constructor if needed
    public ScheduledReport(String email, String frequency) {
        this.email = email;
        this.frequency = frequency;
        this.nextSendDate = LocalDate.now().plusWeeks(1).format(DateTimeFormatter.ISO_DATE);
    }
}