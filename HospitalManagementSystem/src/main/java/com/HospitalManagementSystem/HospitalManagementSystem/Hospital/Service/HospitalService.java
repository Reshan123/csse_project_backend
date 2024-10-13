package com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Model.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalService {
    List<Hospital> getAllHospitals();

    Optional<Hospital> getHospitalById(String id);

    Hospital addHospital(Hospital hospital);

    Hospital updateHospital(String id, Hospital hospitalDetails);

    void deleteHospital(String id);
}
