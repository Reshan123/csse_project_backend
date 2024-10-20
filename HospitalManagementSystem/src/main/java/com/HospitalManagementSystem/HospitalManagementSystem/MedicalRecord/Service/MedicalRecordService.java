package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.Treatments;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {

    List<MedicalRecord> getAllMedicalRecords();

    Optional<MedicalRecord> getMedicalRecordById(String id);

    MedicalRecord addMedicalRecord(MedicalRecord record);

    MedicalRecord updateMedicalRecord(String id,MedicalRecord recordDetails);

    void deleteRecord(String id);

    MedicalRecord addTreatmentToMedicalRecord(String medicalRecordId, Treatments treatment);

    MedicalRecord updateTreatmentInMedicalRecord(String medicalRecordId, String treatmentId, Treatments treatmentDetails);

    MedicalRecord deleteTreatmentFromMedicalRecord(String medicalRecordId, String treatmentId);

    MedicalRecord getMedicalRecordsByUserId(String userId);

}
