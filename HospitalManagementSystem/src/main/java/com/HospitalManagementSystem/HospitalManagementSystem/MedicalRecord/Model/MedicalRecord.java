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

    public MedicalRecord(String id, String userId, String patientId, String firstName, String lastName, Date dateOfBirth, String gender, String contactNumber, String address, List<String> allergies, List<String> ongoingMedications, String emergencyContactName, String emergencyContactNumber) {
        this.id = id;
        this.userId = userId;
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.allergies = allergies;
        this.ongoingMedications = ongoingMedications;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
    }

    private String gender;
    private String contactNumber;
    private String address;
    private List<String> allergies;
    private List<String> ongoingMedications;
    private String emergencyContactName;
    private String emergencyContactNumber;

    private List<Treatments> treatments;

    public MedicalRecord() {

    }
}
