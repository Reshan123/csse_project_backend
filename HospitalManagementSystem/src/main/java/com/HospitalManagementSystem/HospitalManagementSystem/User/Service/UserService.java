package com.HospitalManagementSystem.HospitalManagementSystem.User.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> getAllUsers();

    Optional<UserModel> getUserById(String id);

    UserModel addUser(UserModel user);

    UserModel updateUser(String id,UserModel user);

    void deleteUser(String id);

}
