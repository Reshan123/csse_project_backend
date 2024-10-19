package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import lombok.Data;

@Data
public class PatientDTO extends UserDTO {

    private MedicalRecord medicalrecord;

    public PatientDTO() {
    }

    public PatientDTO(String id, String username, String email,String link, MedicalRecord medicalrecord) {
        super(id, username, email,link);  // Inherit fields from UserDTO
        this.medicalrecord = medicalrecord;
    }

}
