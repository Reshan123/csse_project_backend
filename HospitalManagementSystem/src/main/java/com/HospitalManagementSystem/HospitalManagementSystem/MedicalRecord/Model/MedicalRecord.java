package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "medicalRecords")
@Data
public class MedicalRecord {

    @Id
    private String id;

    private String userId;
    private String patientId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String contactNumber;
    private String address;
    private List<String> allergies;
    private List<String> ongoingMedications;
    private String emergencyContactName;
    private String emergencyContactNumber;

    private List<Treatments> treatments;

}
