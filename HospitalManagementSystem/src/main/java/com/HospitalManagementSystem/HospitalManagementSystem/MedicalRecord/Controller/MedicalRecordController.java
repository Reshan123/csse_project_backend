package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Controller;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service.MedicalRecordService;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.Treatments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicalRecords")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    //Get all records
    @GetMapping("/getAll")
    public List<MedicalRecord> getAllRecords(){
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MedicalRecord> getRecordById(@PathVariable String id) {
        Optional<MedicalRecord> record = medicalRecordService.getMedicalRecordById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/addRecord")
    public MedicalRecord createRecord(@RequestBody MedicalRecord medicalRecord) {
        // Ensure treatments list is initialized
        if (medicalRecord.getTreatments() == null) {
            medicalRecord.setTreatments(new ArrayList<>());
        }
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }


    @PutMapping("/updateRecord/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String id,@RequestBody MedicalRecord medicalRecord){
        MedicalRecord updatedRecord = medicalRecordService.updateMedicalRecord(id,medicalRecord);
        if (updatedRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(String id){
        medicalRecordService.deleteRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Add a treatment to a medical record
    @PostMapping("/{medicalRecordId}/addTreatment")
    public ResponseEntity<MedicalRecord> addTreatmentToMedicalRecord(
            @PathVariable String medicalRecordId,
            @RequestBody Treatments treatment) {
        MedicalRecord updatedRecord = medicalRecordService.addTreatmentToMedicalRecord(medicalRecordId, treatment);
        if (updatedRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedRecord);
    }


    // Update a treatment in a medical record
    @PutMapping("/{medicalRecordId}/updateTreatment/{treatmentId}")
    public ResponseEntity<MedicalRecord> updateTreatmentInMedicalRecord(
            @PathVariable String medicalRecordId,
            @PathVariable String treatmentId,
            @RequestBody Treatments treatmentDetails) {
        MedicalRecord updatedRecord = medicalRecordService.updateTreatmentInMedicalRecord(medicalRecordId, treatmentId, treatmentDetails);
        return ResponseEntity.ok(updatedRecord);
    }

    // Delete a treatment from a medical record
    @DeleteMapping("/{medicalRecordId}/deleteTreatment/{treatmentId}")
    public ResponseEntity<MedicalRecord> deleteTreatmentFromMedicalRecord(
            @PathVariable String medicalRecordId,
            @PathVariable String treatmentId) {
        MedicalRecord updatedRecord = medicalRecordService.deleteTreatmentFromMedicalRecord(medicalRecordId, treatmentId);
        return ResponseEntity.ok(updatedRecord);
    }
}
