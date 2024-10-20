package com.HospitalManagementSystem.HospitalManagementSystem.User.Service;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Doctor;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.ERole;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository.RoleRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository.UserRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Repository.MedicalRecordRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.PatientDTO;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PatientDTO getPatientById(String id) {
        // Fetch user by id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch medical record by user ID if available
        MedicalRecord medicalRecord = medicalRecordRepository.findByUserId(id);
        // Return PatientDTO including the medical record if present
        return new PatientDTO(user.getId(), user.getUsername(), user.getEmail(),user.getLink(),
                medicalRecord);
    }


    @Override
    public List<User> findByRoles(Role role) {
        Role patientRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        return userRepository.findByRoles(patientRole);
    }

    @Override
    public List<Doctor> findDoctors(Role role) {
        Role patientRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        return userRepository.findDoctorsByRoles(patientRole);
    }

}
