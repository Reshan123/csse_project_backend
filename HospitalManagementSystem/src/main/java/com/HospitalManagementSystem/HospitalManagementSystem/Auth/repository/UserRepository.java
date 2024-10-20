package com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository;

import java.util.List;
import java.util.Optional;

import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Doctor;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  List<User> findByRoles(Role patientRole);

  @Query("{ '_class': 'com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Doctor' }")
  List<Doctor> findAllDoctors();

}
