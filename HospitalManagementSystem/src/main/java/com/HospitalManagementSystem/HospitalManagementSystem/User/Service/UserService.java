package com.HospitalManagementSystem.HospitalManagementSystem.User.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserDTO;

public interface UserService {
    UserDTO getPatientById(String id);
}
