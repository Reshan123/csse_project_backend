package com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Controller;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;
import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Service.ScheduledReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scheduled-reports")
public class ScheduledReportController {

    @Autowired
    private ScheduledReportService scheduledReportService;

    @PostMapping
    public ResponseEntity<ScheduledReport> createReport(@RequestBody ScheduledReport report) {
        ScheduledReport createdReport = scheduledReportService.createScheduledReport(report);
        return ResponseEntity.ok(createdReport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledReport> getReportById(@PathVariable String id) {
        Optional<ScheduledReport> report = scheduledReportService.getScheduledReportById(id);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ScheduledReport>> getAllReports() {
        List<ScheduledReport> reports = scheduledReportService.getAllScheduledReports();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduledReport> updateReport(@PathVariable String id, @RequestBody ScheduledReport report) {
        ScheduledReport updatedReport = scheduledReportService.updateScheduledReport(id, report);
        if (updatedReport != null) {
            return ResponseEntity.ok(updatedReport);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("emailSent/{id}")
    public ResponseEntity<ScheduledReport> updateEmailSent(@PathVariable String id) {
        ScheduledReport updatedReport = scheduledReportService.emailSent(id);
        if (updatedReport != null) {
            return ResponseEntity.ok(updatedReport);
        }
        System.out.println("ScheduledReport with id {} not found" + id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        scheduledReportService.deleteScheduledReport(id);
        return ResponseEntity.noContent().build();
    }
}