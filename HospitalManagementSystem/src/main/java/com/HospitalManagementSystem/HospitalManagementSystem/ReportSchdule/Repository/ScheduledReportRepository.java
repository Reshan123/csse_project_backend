package com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportSchdule.Model.ScheduledReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledReportRepository extends MongoRepository<ScheduledReport, String> {
    // Custom queries can go here if needed
}
