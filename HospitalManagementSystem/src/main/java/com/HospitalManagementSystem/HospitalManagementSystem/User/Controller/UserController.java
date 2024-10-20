package com.HospitalManagementSystem.HospitalManagementSystem.User.Controller;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.ERole;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.PatientDTO;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    // Get Patient Details with Medical Record by ID
    @GetMapping("patientdetails/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable String id) {
        PatientDTO patientDTO = userService.getPatientById(id);
        System.out.println(patientDTO.getMedicalrecord());
        return ResponseEntity.ok(patientDTO);
    }
    @GetMapping("all")
    public ResponseEntity<List<User>> getAllPatients() {

        return ResponseEntity.ok(userService.findByRoles(new Role(ERole.ROLE_USER)));
    }
    @GetMapping("doctors")
    public ResponseEntity<List<User>> getAllDoctors() {

        return ResponseEntity.ok(userService.findByRoles(new Role(ERole.ROLE_MODERATOR)));
    }
}
