package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
public class Treatments {

    @Id
    private String id;

    private String patientID;
    private String aptNo;
    private String treatmentType;
    private String prescription;
    private String contactInfo;
}
