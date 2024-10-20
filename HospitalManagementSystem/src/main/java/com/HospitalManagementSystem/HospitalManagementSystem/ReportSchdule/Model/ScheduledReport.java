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

    // Setter for email with validation
    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email should be valid");
        }
        this.email = email;
    }

    // Setter for frequency with validation
    public void setFrequency(String frequency) {
        if (frequency == null || (!frequency.equalsIgnoreCase("daily") &&
                !frequency.equalsIgnoreCase("weekly") &&
                !frequency.equalsIgnoreCase("monthly"))) {
            throw new IllegalArgumentException("Frequency must be either daily, weekly, or monthly");
        }
        this.frequency = frequency;
    }

    // Setter for nextSendDate with validation
    public void setNextSendDate(String nextSendDate) {
        if (nextSendDate == null || !nextSendDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new IllegalArgumentException("Next send date must be in the format YYYY-MM-DD");
        }

        LocalDate date = LocalDate.parse(nextSendDate);
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Next send date cannot be in the past.");
        }

        this.nextSendDate = nextSendDate;
    }

    // Optionally, you can add a method to convert the date format to LocalDate
    public LocalDate getFormattedNextSendDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(nextSendDate, formatter);
    }
}
