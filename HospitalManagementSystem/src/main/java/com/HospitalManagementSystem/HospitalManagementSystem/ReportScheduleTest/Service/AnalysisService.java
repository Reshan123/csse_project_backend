package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Model.Analysis;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

    List<Analysis> getAppointmentsData();
    List<Analysis> getMedicalRecordsData();
    Map<String, Integer> getTreatmentsPerPatient();
    Map<String, Integer> getTreatmentsPerGender();
}
