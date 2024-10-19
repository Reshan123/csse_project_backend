package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(String id);

    List<Appointment> getAllAppointmentsByPatientID(String id);

    Appointment addAppointment(Appointment appointment);

    Appointment updateAppointment(String id, Appointment appointmentDetails);

    void deleteAppointment(String id);
}
