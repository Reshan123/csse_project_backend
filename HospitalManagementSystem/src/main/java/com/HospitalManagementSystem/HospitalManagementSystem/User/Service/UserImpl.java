package com.HospitalManagementSystem.HospitalManagementSystem.User.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserModel;

import java.util.List;
import java.util.Optional;

public class UserImpl implements UserService {


    @Override
    public List<UserModel> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserModel> getUserById(String id) {
        return Optional.empty();
    }

    @Override
    public UserModel addUser(UserModel user) {
        return null;
    }

    @Override
    public UserModel updateUser(String id, UserModel user) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }
}
