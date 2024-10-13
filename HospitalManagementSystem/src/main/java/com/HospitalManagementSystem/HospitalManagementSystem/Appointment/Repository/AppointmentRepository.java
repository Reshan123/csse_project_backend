package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

}
