package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Repository.MedicalRecordRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.Treatments;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (record.isPresent()) {
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
            existingRecord.setTreatments(recordDetails.getTreatments());

            return medicalRecordRepo.save(existingRecord);
        } else {
            throw new RuntimeException("Medical record not found with id: " + id);
        }
    }

    @Override
    public void deleteRecord(String id) {
        medicalRecordRepo.deleteById(id);
    }

    // Add a new treatment to an existing medical record
    public MedicalRecord addTreatmentToMedicalRecord(String medicalRecordId, Treatments treatment) {
        Optional<MedicalRecord> record = medicalRecordRepo.findById(medicalRecordId);

        if (record.isPresent()) {
            MedicalRecord existingRecord = record.get();
            existingRecord.getTreatments().add(treatment); // Add the new treatment
            return medicalRecordRepo.save(existingRecord);
        } else {
            throw new RuntimeException("Medical record not found with id: " + medicalRecordId);
        }
    }

    // Update a treatment in an existing medical record
    public MedicalRecord updateTreatmentInMedicalRecord(String medicalRecordId, String treatmentId, Treatments treatmentDetails) {
        Optional<MedicalRecord> record = medicalRecordRepo.findById(medicalRecordId);

        if (record.isPresent()) {
            MedicalRecord existingRecord = record.get();
            List<Treatments> treatments = existingRecord.getTreatments();

            for (int i = 0; i < treatments.size(); i++) {
                if (treatments.get(i).getId().equals(treatmentId)) {
                    treatments.set(i, treatmentDetails);
                    existingRecord.setTreatments(treatments);
                    return medicalRecordRepo.save(existingRecord);
                }
            }

            throw new RuntimeException("Treatment not found with id: " + treatmentId);
        } else {
            throw new RuntimeException("Medical record not found with id: " + medicalRecordId);
        }
    }

    // Delete a treatment from an existing medical record
    public MedicalRecord deleteTreatmentFromMedicalRecord(String medicalRecordId, String treatmentId) {
        Optional<MedicalRecord> record = medicalRecordRepo.findById(medicalRecordId);

        if (record.isPresent()) {
            MedicalRecord existingRecord = record.get();
            List<Treatments> treatments = existingRecord.getTreatments();

            treatments.removeIf(t -> t.getId().equals(treatmentId));
            existingRecord.setTreatments(treatments);
            return medicalRecordRepo.save(existingRecord);
        } else {
            throw new RuntimeException("Medical record not found with id: " + medicalRecordId);
        }
    }


    @Override
    public List<MedicalRecord> getMedicalRecordsByUserId(String userId) {
        return medicalRecordRepo.findByUserId(userId);
    }

}
