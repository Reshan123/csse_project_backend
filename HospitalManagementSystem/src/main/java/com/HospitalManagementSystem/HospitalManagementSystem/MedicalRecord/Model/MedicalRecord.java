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

    public void setPatientId(String patientId){
        if(patientId==null||patientId.isEmpty()){
            throw new IllegalArgumentException("Patient id cannot be null or empty");
        }
        this.patientId=patientId;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        if (dateOfBirth.after(new Date())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        if (gender == null || (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female"))) {
            throw new IllegalArgumentException("Gender must be 'Male' or 'Female'");
        }
        this.gender = gender;
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.length() < 10) {
            throw new IllegalArgumentException("Contact number must be at least 10 digits");
        }
        if (!contactNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Contact number must contain only digits");
        }
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address;
    }

    public void setAllergies(List<String> allergies) {
        if (allergies == null) {
            throw new IllegalArgumentException("Allergies list cannot be null");
        }
        this.allergies = allergies;
    }

    public void setOngoingMedications(List<String> ongoingMedications) {
        if (ongoingMedications == null) {
            throw new IllegalArgumentException("Ongoing medications list cannot be null");
        }
        this.ongoingMedications = ongoingMedications;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        if (emergencyContactName == null || emergencyContactName.isEmpty()) {
            throw new IllegalArgumentException("Emergency contact name cannot be null or empty");
        }
        this.emergencyContactName = emergencyContactName;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        if (emergencyContactNumber == null || emergencyContactNumber.length() < 10) {
            throw new IllegalArgumentException("Emergency contact number must be at least 10 digits");
        }
        if (!emergencyContactNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Emergency contact number must contain only digits");
        }
        this.emergencyContactNumber = emergencyContactNumber;
    }



}
