package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findAllByPatientID(String patientID);
}
