package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(String aptNo) {
        return appointmentRepo.findById(aptNo);
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        appointment.setCreatedAt(new Date());  // Set creation date
        appointment.setUpdatedAt(new Date());  // Set initial update date
        return appointmentRepo.save(appointment);
    }

    @Override
    public Appointment updateAppointment(String aptNo, Appointment appointmentDetails) {
        Optional<Appointment> appointmentOptional = appointmentRepo.findById(aptNo);

        if (appointmentOptional.isPresent()) {
            Appointment existingAppointment = appointmentOptional.get();

            // Update appointment fields
            existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            existingAppointment.setPatientID(appointmentDetails.getPatientID());
            existingAppointment.setPatientName(appointmentDetails.getPatientName());
            existingAppointment.setDocID(appointmentDetails.getDocID());
            existingAppointment.setDocName(appointmentDetails.getDocName());
            existingAppointment.setReason(appointmentDetails.getReason());
            existingAppointment.setDepartment(appointmentDetails.getDepartment());
            existingAppointment.setStatus(appointmentDetails.getStatus());
            existingAppointment.setUpdatedAt(new Date());  // Set updated time

            // Save and return the updated appointment
            return appointmentRepo.save(existingAppointment);
        } else {
            throw new RuntimeException("Appointment not found with appointment number: " + aptNo);
        }
    }

    @Override
    public void deleteAppointment(String aptNo) {
        appointmentRepo.deleteById(aptNo);
    }


}
