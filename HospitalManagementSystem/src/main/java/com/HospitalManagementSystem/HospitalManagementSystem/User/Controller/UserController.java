package com.HospitalManagementSystem.HospitalManagementSystem.User.Controller;

import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.PatientDTO;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserDTO;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Get Patient Details with Medical Record by ID
    @GetMapping("patientdetails/{id}")
    public ResponseEntity<UserDTO> getPatientById(@PathVariable String id) {
        UserDTO patientDTO = userService.getPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }
}
