package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecords.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalRecordNegativeTest {


    @Test
    public void testSetPatientId_Empty() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setPatientId("");
        }, "Patient ID is mandatory");
    }

    @Test
    public void testSetFirstName_Empty() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setFirstName("");
        }, "First name is mandatory");
    }

    @Test
    public void testSetLastName_Empty() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setLastName("");
        }, "Last name is mandatory");
    }

    @Test
    public void testSetDateOfBirth_FutureDate() {
        MedicalRecord medicalRecord = new MedicalRecord();
        Date futureDate = new Date(System.currentTimeMillis() + 10000000); // Future date
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setDateOfBirth(futureDate);
        }, "Date of birth cannot be a future date");
    }

    @Test
    public void testSetGender_InvalidValue() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setGender("Unknown");
        }, "Gender must be 'Male' or 'Female'");
    }

    @Test
    public void testSetContactNumber_InvalidFormat() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setContactNumber("123"); // Too short
        }, "Contact number must be at least 10 digits");
    }

    @Test
    public void testSetAddress_Null() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setAddress(null);
        }, "Address is mandatory");
    }

    @Test
    public void testSetEmergencyContactName_Empty() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setEmergencyContactName("");
        }, "Emergency contact name is mandatory");
    }

    @Test
    public void testSetEmergencyContactNumber_InvalidFormat() {
        MedicalRecord medicalRecord = new MedicalRecord();
        assertThrows(IllegalArgumentException.class, () -> {
            medicalRecord.setEmergencyContactNumber("abc"); // Invalid number format
        }, "Emergency contact number must be numeric");
    }
}
