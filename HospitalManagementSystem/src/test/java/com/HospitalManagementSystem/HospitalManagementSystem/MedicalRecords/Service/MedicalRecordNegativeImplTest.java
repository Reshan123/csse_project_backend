package com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecords.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.Treatments;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Repository.MedicalRecordRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Service.MedicalRecordImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicalRecordNegativeImplTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepo;

    @InjectMocks
    private MedicalRecordImpl medicalRecordService;

    private MedicalRecord testRecord;
    private Treatments testTreatment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Test medical record
        testRecord = new MedicalRecord();
        testRecord.setId("MR001");
        testRecord.setUserId("USER001");
        testRecord.setPatientId("P001");
        testRecord.setFirstName("John");
        testRecord.setLastName("Doe");
        testRecord.setDateOfBirth(new java.util.Date());
        testRecord.setGender("Male");
        testRecord.setContactNumber("1234567890");
        testRecord.setAddress("123 Street");
        testRecord.setTreatments(new java.util.ArrayList<>());

        // Test treatment
        testTreatment = new Treatments();
        testTreatment.setId("T001");
        testTreatment.setTreatmentDetails("Blood Pressure Check");
        testTreatment.setDate(new java.util.Date());
    }

    @Test
    public void testAddTreatmentToMedicalRecord_NotFound() {
        // Arrange: Mock repository to return empty Optional
        when(medicalRecordRepo.findById("MR001")).thenReturn(Optional.empty());

        // Act & Assert: Expect a RuntimeException to be thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicalRecordService.addTreatmentToMedicalRecord("MR001", testTreatment);
        });

        assertEquals("Medical record not found with id: MR001", exception.getMessage());
        verify(medicalRecordRepo, times(1)).findById("MR001");
        verify(medicalRecordRepo, times(0)).save(any(MedicalRecord.class));
    }

    @Test
    public void testUpdateMedicalRecord_NotFound() {
        // Arrange: Mock repository to return empty Optional
        when(medicalRecordRepo.findById("MR002")).thenReturn(Optional.empty());

        // Act & Assert: Expect a RuntimeException to be thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicalRecordService.updateMedicalRecord("MR002", testRecord);
        });

        assertEquals("Medical record not found with id: MR002", exception.getMessage());
        verify(medicalRecordRepo, times(1)).findById("MR002");
        verify(medicalRecordRepo, times(0)).save(any(MedicalRecord.class));
    }

    @Test
    public void testDeleteTreatmentFromMedicalRecord_NotFound() {
        // Arrange: Mock repository to return empty Optional
        when(medicalRecordRepo.findById("MR001")).thenReturn(Optional.empty());

        // Act & Assert: Expect a RuntimeException to be thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicalRecordService.deleteTreatmentFromMedicalRecord("MR001", "T001");
        });

        assertEquals("Medical record not found with id: MR001", exception.getMessage());
        verify(medicalRecordRepo, times(1)).findById("MR001");
        verify(medicalRecordRepo, times(0)).save(any(MedicalRecord.class));
    }
}
