package com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Controller;

import com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Model.Analysis;
import com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Service.AnalysisService;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/getAppointmentData")
    public List<Analysis> getAllAppointments() {
        return analysisService.getAppointmentsData();
    }

    // Get data for treatments per patient
    @GetMapping("/treatments-per-patient")
    public Map<String, Integer> getTreatmentsPerPatient() {
        return analysisService.getTreatmentsPerPatient();
    }

    // Get data for treatments per gender
    @GetMapping("/treatments-per-gender")
    public Map<String, Integer> getTreatmentsPerGender() {
        return analysisService.getTreatmentsPerGender();
    }
}
