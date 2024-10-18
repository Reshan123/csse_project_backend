package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;

public class PatientDTO extends UserDTO {

    private MedicalRecord medicalrecord;

    public PatientDTO() {
    }

    public PatientDTO(String id, String username, String email, MedicalRecord medicalrecord) {
        super(id, username, email);  // Inherit fields from UserDTO
        this.medicalrecord = medicalrecord;
    }

}
