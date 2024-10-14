package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Controller;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public MedicalRecord createRecord(@RequestBody MedicalRecord medicalRecord){
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
}
