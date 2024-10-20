package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Model.Analysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends MongoRepository<Analysis, String> {

}
