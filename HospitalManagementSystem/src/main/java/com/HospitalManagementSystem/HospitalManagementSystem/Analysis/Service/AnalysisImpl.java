package com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Model.Analysis;
import com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Repository.AnalysisRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalysisImpl implements AnalysisService {

    @Autowired
    private AnalysisRepository analysisRepository;
    private AppointmentService appointmentService;


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
}
