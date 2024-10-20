package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Model.Analysis;
import com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Repository.AnalysisRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Service.AppointmentService;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.Treatments;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalysisImpl implements AnalysisService {

    @Autowired
    private AnalysisRepository analysisRepository;

    @Autowired
    private AppointmentService appointmentService; // Autowire appointmentService here

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public List<Analysis> getAppointmentsData() {
        // Fetch all appointments
        List<Appointment> appointments = appointmentService.getAllAppointments();

        // Map to hold the date and the number of appointments on that date
        Map<String, Integer> appointmentCountByDate = new HashMap<>();

        // Iterate through the appointments
        for (Appointment appointment : appointments) {
            // Extract the appointment date as a string (format it if necessary)
            String date = appointment.getAppointmentDate().toString();

            // Update the count of appointments for the given date
            appointmentCountByDate.put(date, appointmentCountByDate.getOrDefault(date, 0) + 1);
        }

        // Prepare lists for data and labels
        List<String> labels = new ArrayList<>(appointmentCountByDate.keySet());
        List<String> data = new ArrayList<>();

        // Convert counts to strings for the data list
        for (String date : labels) {
            data.add(appointmentCountByDate.get(date).toString());
        }

        // Create an Analysis object
        Analysis analysis = new Analysis();
        analysis.setLabels(labels);
        analysis.setData(data);

        // Return the analysis list
        return List.of(analysis);
    }

    @Override
    public List<Analysis> getMedicalRecordsData() {
        return List.of();
    }


    // Combine data for a chart: e.g., number of treatments per patient
    @Override
    public Map<String, Integer> getTreatmentsPerPatient() {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        Map<String, Integer> treatmentsPerPatient = new HashMap<>();

        for (MedicalRecord record : medicalRecords) {
            // Fetch treatments for the patient directly from the medical record
            List<Treatments> treatments = record.getTreatments();
            int treatmentCount = treatments != null ? treatments.size() : 0;

            // Use patient's full name as the key
            String patientName = record.getFirstName() + " " + record.getLastName();
            System.out.println(patientName + " " + treatmentCount);
            // If the patient name already exists, add the new treatment count to the existing one
            treatmentsPerPatient.put(patientName, treatmentsPerPatient.getOrDefault(patientName, 0) + treatmentCount);
        }

        return treatmentsPerPatient;
    }

    // Other combinations: e.g., treatments per gender, age distribution, etc.
    @Override
    public Map<String, Integer> getTreatmentsPerGender() {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        Map<String, Integer> treatmentsPerGender = new HashMap<>();

        for (MedicalRecord record : medicalRecords) {
            // Fetch treatments for the patient directly from the medical record
            List<Treatments> treatments = record.getTreatments();
            System.out.println(record);
            int treatmentCount = treatments != null ? treatments.size() : 0;

            // Use gender as the key and update the count
            String gender = record.getGender().toLowerCase();

            treatmentsPerGender.put(gender, treatmentsPerGender.getOrDefault(gender, 0) + treatmentCount);
        }

        return treatmentsPerGender;
    }
}
