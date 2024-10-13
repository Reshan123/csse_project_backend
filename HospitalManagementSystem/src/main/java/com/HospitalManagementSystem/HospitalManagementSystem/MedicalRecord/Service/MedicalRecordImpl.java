package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MedicalRecordImpl implements MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepo;

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepo.findAll();
    }

    @Override
    public Optional<MedicalRecord> getMedicalRecordById(String id) {
        return medicalRecordRepo.findById(id);
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord record) {
        return medicalRecordRepo.save(record);
    }

    @Override
    public MedicalRecord updateMedicalRecord(String id, MedicalRecord recordDetails) {
        Optional<MedicalRecord> record = medicalRecordRepo.findById(id);

        if(record.isPresent()){
            MedicalRecord existingRecord = record.get();

            existingRecord.setPatientId(recordDetails.getPatientId());
            existingRecord.setFirstName(recordDetails.getFirstName());
            existingRecord.setLastName(recordDetails.getLastName());
            existingRecord.setDateOfBirth(recordDetails.getDateOfBirth());
            existingRecord.setGender(recordDetails.getGender());
            existingRecord.setContactNumber(recordDetails.getContactNumber());
            existingRecord.setAddress(recordDetails.getAddress());
            existingRecord.setAllergies(recordDetails.getAllergies());
            existingRecord.setOngoingMedications(recordDetails.getOngoingMedications());
            existingRecord.setEmergencyContactName(recordDetails.getEmergencyContactName());
            existingRecord.setEmergencyContactNumber(recordDetails.getEmergencyContactNumber());

            return medicalRecordRepo.save(existingRecord);
        }else {
            throw new RuntimeException("Medical record not found with id: " + id);
        }
    }

    @Override
    public void deleteRecord(String id) {
        medicalRecordRepo.deleteById(id);
    }
}
