package com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Model.Hospital;
import com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

    @Service
    public class HospitalServiceImpl implements HospitalService {

        @Autowired
        private HospitalRepository hospitalRepository;

        @Override
        public List<Hospital> getAllHospitals() {
            return hospitalRepository.findAll();
        }

        @Override
        public Optional<Hospital> getHospitalById(String id) {
            return hospitalRepository.findById(id);
        }

        @Override
        public Hospital addHospital(Hospital hospital) {
            return hospitalRepository.save(hospital);
        }

        @Override
        public Hospital updateHospital(String id, Hospital hospitalDetails) {
            Optional<Hospital> hospital = hospitalRepository.findById(id);
            if (hospital.isPresent()) {
                Hospital existingHospital = hospital.get();
                existingHospital.setName(hospitalDetails.getName());
                existingHospital.setLocation(hospitalDetails.getLocation());
                existingHospital.setContactInfo(hospitalDetails.getContactInfo());
                return hospitalRepository.save(existingHospital);
            } else {
                return null;
            }
        }

        @Override
        public void deleteHospital(String id) {
            hospitalRepository.deleteById(id);
        }
    }


