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
    private Boolean emailSent;
}