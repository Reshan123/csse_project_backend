package com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Model.Analysis;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AnalysisService {

    List<Analysis> getAppointmentsData();
    List<Analysis> getMedicalRecordsData();
}
