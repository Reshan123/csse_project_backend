package com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Controller;
import com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Model.Hospital;
import com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // Get all hospitals
    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    // Get hospital by ID
    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable String id) {
        Optional<Hospital> hospital = hospitalService.getHospitalById(id);
        return hospital.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new hospital
    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    // Update a hospital
    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable String id, @RequestBody Hospital hospitalDetails) {
        Hospital updatedHospital = hospitalService.updateHospital(id, hospitalDetails);
        if (updatedHospital == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHospital);
    }

    // Delete a hospital
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }
}
