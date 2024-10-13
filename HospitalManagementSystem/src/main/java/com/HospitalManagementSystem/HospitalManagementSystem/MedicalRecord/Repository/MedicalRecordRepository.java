package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    // You can define additional custom query methods here if needed
}
