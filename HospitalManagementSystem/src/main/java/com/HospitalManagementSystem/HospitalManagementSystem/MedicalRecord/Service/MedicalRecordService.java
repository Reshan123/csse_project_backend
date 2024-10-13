package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {

    List<MedicalRecord> getAllMedicalRecords();

    Optional<MedicalRecord> getMedicalRecordById(String id);

    MedicalRecord addMedicalRecord(MedicalRecord record);

    MedicalRecord updateMedicalRecord(String id,MedicalRecord recordDetails);

    void deleteRecord(String id);
}
