package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecords.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.Treatments;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicalRecordTest {

    @Test
    public void testMedicalRecordConstructorAndGetters() {

        String id = "MR001";
        String userId = "U001";
        String patientId = "P001";
        String firstName = "Jane";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String gender = "Female";
        String contactNumber = "1234567890";
        String address = "123 Main St";
        List<String> allergies = Arrays.asList("Peanuts", "Dust");
        List<String> ongoingMedications = Arrays.asList("Ibuprofen");
        String emergencyContactName = "John Doe";
        String emergencyContactNumber = "0987654321";
        List<Treatments> treatments = List.of(new Treatments());

        MedicalRecord medicalRecord = new MedicalRecord(id, userId, patientId, firstName, lastName, dateOfBirth,
                gender, contactNumber, address, allergies, ongoingMedications, emergencyContactName, emergencyContactNumber);
        medicalRecord.setTreatments(treatments);

        assertEquals(id, medicalRecord.getId());
        assertEquals(userId, medicalRecord.getUserId());
        assertEquals(patientId, medicalRecord.getPatientId());
        assertEquals(firstName, medicalRecord.getFirstName());
        assertEquals(lastName, medicalRecord.getLastName());
        assertEquals(dateOfBirth, medicalRecord.getDateOfBirth());
        assertEquals(gender, medicalRecord.getGender());
        assertEquals(contactNumber, medicalRecord.getContactNumber());
        assertEquals(address, medicalRecord.getAddress());
        assertEquals(allergies, medicalRecord.getAllergies());
        assertEquals(ongoingMedications, medicalRecord.getOngoingMedications());
        assertEquals(emergencyContactName, medicalRecord.getEmergencyContactName());
        assertEquals(emergencyContactNumber, medicalRecord.getEmergencyContactNumber());
        assertEquals(treatments, medicalRecord.getTreatments());
    }

    @Test
    public void testMedicalRecordSetters() {
        MedicalRecord medicalRecord = new MedicalRecord();

        String id = "MR001";
        String userId = "U001";
        String patientId = "P001";
        String firstName = "Jane";
        String lastName = "Doe";
        Date dateOfBirth = new Date();
        String gender = "Female";
        String contactNumber = "1234567890";
        String address = "123 Main St";
        List<String> allergies = Arrays.asList("Peanuts", "Dust");
        List<String> ongoingMedications = Arrays.asList("Ibuprofen");
        String emergencyContactName = "John Doe";
        String emergencyContactNumber = "0987654321";
        List<Treatments> treatments = Arrays.asList(new Treatments());

        medicalRecord.setId(id);
        medicalRecord.setUserId(userId);
        medicalRecord.setPatientId(patientId);
        medicalRecord.setFirstName(firstName);
        medicalRecord.setLastName(lastName);
        medicalRecord.setDateOfBirth(dateOfBirth);
        medicalRecord.setGender(gender);
        medicalRecord.setContactNumber(contactNumber);
        medicalRecord.setAddress(address);
        medicalRecord.setAllergies(allergies);
        medicalRecord.setOngoingMedications(ongoingMedications);
        medicalRecord.setEmergencyContactName(emergencyContactName);
        medicalRecord.setEmergencyContactNumber(emergencyContactNumber);
        medicalRecord.setTreatments(treatments);

        assertEquals(id, medicalRecord.getId());
        assertEquals(userId, medicalRecord.getUserId());
        assertEquals(patientId, medicalRecord.getPatientId());
        assertEquals(firstName, medicalRecord.getFirstName());
        assertEquals(lastName, medicalRecord.getLastName());
        assertEquals(dateOfBirth, medicalRecord.getDateOfBirth());
        assertEquals(gender, medicalRecord.getGender());
        assertEquals(contactNumber, medicalRecord.getContactNumber());
        assertEquals(address, medicalRecord.getAddress());
        assertEquals(allergies, medicalRecord.getAllergies());
        assertEquals(ongoingMedications, medicalRecord.getOngoingMedications());
        assertEquals(emergencyContactName, medicalRecord.getEmergencyContactName());
        assertEquals(emergencyContactNumber, medicalRecord.getEmergencyContactNumber());
        assertEquals(treatments, medicalRecord.getTreatments());
    }
}
