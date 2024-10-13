package com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Model.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    // You can add custom queries here if needed
}
