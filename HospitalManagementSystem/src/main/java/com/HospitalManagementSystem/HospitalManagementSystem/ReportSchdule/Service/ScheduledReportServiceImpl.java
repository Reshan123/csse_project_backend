package com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;
import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Repository.ScheduledReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledReportServiceImpl implements ScheduledReportService {

    @Autowired
    private ScheduledReportRepository scheduledReportRepository;

    @Override
    public ScheduledReport createScheduledReport(ScheduledReport report) {
        // Calculate nextSendDate based on frequency
        String nextSendDate = calculateNextSendDate(report.getFrequency());
        report.setNextSendDate(nextSendDate);
        return scheduledReportRepository.save(report);
    }

    @Override
    public Optional<ScheduledReport> getScheduledReportById(String id) {
        return scheduledReportRepository.findById(id);
    }

    @Override
    public List<ScheduledReport> getAllScheduledReports() {
        return scheduledReportRepository.findAll();
    }

    @Override
    public ScheduledReport updateScheduledReport(String id, ScheduledReport report) {
        Optional<ScheduledReport> existingReport = scheduledReportRepository.findById(id);
        if (existingReport.isPresent()) {
            ScheduledReport updatedReport = existingReport.get();
            updatedReport.setEmail(report.getEmail());
            updatedReport.setFrequency(report.getFrequency());
            // Calculate nextSendDate based on the new frequency
            updatedReport.setNextSendDate(calculateNextSendDate(report.getFrequency()));
            return scheduledReportRepository.save(updatedReport);
        }
        return null;
    }

    @Override
    public void deleteScheduledReport(String id) {
        scheduledReportRepository.deleteById(id);
    }

    // Helper method to calculate the next send date based on frequency
    private String calculateNextSendDate(String frequency) {
        LocalDate currentDate = LocalDate.now();
        switch (frequency.toLowerCase()) {
            case "daily":
                return currentDate.plusDays(1).format(DateTimeFormatter.ISO_DATE);
            case "weekly":
                return currentDate.plusWeeks(1).format(DateTimeFormatter.ISO_DATE);
            case "monthly":
                return currentDate.plusMonths(1).format(DateTimeFormatter.ISO_DATE);
            default:
                throw new IllegalArgumentException("Invalid frequency: " + frequency);
        }
    }
}
