package com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;

import java.util.List;
import java.util.Optional;

public interface ScheduledReportService {
    ScheduledReport createScheduledReport(ScheduledReport report);
    Optional<ScheduledReport> getScheduledReportById(String id);
    List<ScheduledReport> getAllScheduledReports();
    ScheduledReport updateScheduledReport(String id, ScheduledReport report);
    ScheduledReport emailSent(String id);
    void deleteScheduledReport(String id);
}
