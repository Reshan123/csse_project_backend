package com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Repository;

import com.HospitalManagementSystem.HospitalManagementSystem.Analysis.Model.Analysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends MongoRepository<Analysis, String> {

}
