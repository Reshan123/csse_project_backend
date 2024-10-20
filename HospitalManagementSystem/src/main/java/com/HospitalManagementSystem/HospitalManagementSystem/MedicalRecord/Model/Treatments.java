package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
public class Treatments {

    @Id
    private String id;

    private String patientID;
    private String aptNo;
    private String treatmentType;
    private String prescription;
    private String contactInfo;

    public void setTreatmentDetails(String bloodPressureCheck) {
    }

    public void setDate(Date date) {
    }
}
