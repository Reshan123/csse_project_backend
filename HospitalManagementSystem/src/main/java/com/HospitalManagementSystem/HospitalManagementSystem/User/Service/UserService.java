package com.HospitalManagementSystem.HospitalManagementSystem.User.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.PatientDTO;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserDTO;

import java.util.List;

public interface UserService {
    PatientDTO getPatientById(String id);

   List<User> getPatients();
}
