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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicalRecordImplTest {

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
        testRecord.setDateOfBirth(new Date());
        testRecord.setGender("Male");
        testRecord.setContactNumber("1234567890");
        testRecord.setAddress("123 Street");
        testRecord.setAllergies(List.of("Peanuts"));
        testRecord.setOngoingMedications(List.of("Medication1"));
        testRecord.setEmergencyContactName("Jane Doe");
        testRecord.setEmergencyContactNumber("0987654321");
        testRecord.setTreatments(new ArrayList<>());

        // Test treatment
        testTreatment = new Treatments();
        testTreatment.setId("T001");
        testTreatment.setTreatmentDetails("Blood Pressure Check");
        testTreatment.setDate(new Date());
    }

    @Test
    public void testGetAllMedicalRecords() {
        when(medicalRecordRepo.findAll()).thenReturn(List.of(testRecord));

        var result = medicalRecordService.getAllMedicalRecords();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("P001", result.get(0).getPatientId());
        verify(medicalRecordRepo, times(1)).findAll();
    }

    @Test
    public void testGetMedicalRecordById() {
        when(medicalRecordRepo.findById("MR001")).thenReturn(Optional.of(testRecord));

        var result = medicalRecordService.getMedicalRecordById("MR001");

        assertTrue(result.isPresent());
        assertEquals("MR001", result.get().getId());
        verify(medicalRecordRepo, times(1)).findById("MR001");
    }

    @Test
    public void testGetMedicalRecordById_NotFound() {
        when(medicalRecordRepo.findById("MR002")).thenReturn(Optional.empty());

        var result = medicalRecordService.getMedicalRecordById("MR002");

        assertFalse(result.isPresent());
        verify(medicalRecordRepo, times(1)).findById("MR002");
    }

    @Test
    public void testAddMedicalRecord() {
        when(medicalRecordRepo.save(any(MedicalRecord.class))).thenReturn(testRecord);

        var result = medicalRecordService.addMedicalRecord(testRecord);

        assertNotNull(result);
        assertEquals("P001", result.getPatientId());
        verify(medicalRecordRepo, times(1)).save(testRecord);
    }

    @Test
    public void testUpdateMedicalRecord() {
        when(medicalRecordRepo.findById("MR001")).thenReturn(Optional.of(testRecord));
        when(medicalRecordRepo.save(any(MedicalRecord.class))).thenReturn(testRecord);

        MedicalRecord updatedRecord = new MedicalRecord();
        updatedRecord.setFirstName("UpdatedFirstName");
        updatedRecord.setLastName("UpdatedLastName");
        updatedRecord.setPatientId("P002");
        updatedRecord.setDateOfBirth(new Date());
        updatedRecord.setGender("Female");

        var result = medicalRecordService.updateMedicalRecord("MR001", updatedRecord);

        assertNotNull(result);
        assertEquals("UpdatedFirstName", result.getFirstName());
        assertEquals("Female", result.getGender());
        verify(medicalRecordRepo, times(1)).findById("MR001");
        verify(medicalRecordRepo, times(1)).save(any(MedicalRecord.class));
    }

    @Test
    public void testUpdateMedicalRecord_NotFound() {
        when(medicalRecordRepo.findById("MR002")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicalRecordService.updateMedicalRecord("MR002", testRecord);
        });

        assertEquals("Medical record not found with id: MR002", exception.getMessage());
        verify(medicalRecordRepo, times(1)).findById("MR002");
        verify(medicalRecordRepo, times(0)).save(any(MedicalRecord.class));
    }

    @Test
    public void testAddTreatmentToMedicalRecord() {
        when(medicalRecordRepo.findById("MR001")).thenReturn(Optional.of(testRecord));
        when(medicalRecordRepo.save(any(MedicalRecord.class))).thenReturn(testRecord);

        var result = medicalRecordService.addTreatmentToMedicalRecord("MR001", testTreatment);

        assertNotNull(result);
        assertEquals(1, result.getTreatments().size());
        assertEquals("T001", result.getTreatments().get(0).getId());
        verify(medicalRecordRepo, times(1)).findById("MR001");
        verify(medicalRecordRepo, times(1)).save(testRecord);
    }

    @Test
    public void testDeleteTreatmentFromMedicalRecord() {
        testRecord.getTreatments().add(testTreatment);
        when(medicalRecordRepo.findById("MR001")).thenReturn(Optional.of(testRecord));
        when(medicalRecordRepo.save(any(MedicalRecord.class))).thenReturn(testRecord);

        var result = medicalRecordService.deleteTreatmentFromMedicalRecord("MR001", "T001");

        assertNotNull(result);
        assertEquals(0, result.getTreatments().size());
        verify(medicalRecordRepo, times(1)).findById("MR001");
        verify(medicalRecordRepo, times(1)).save(testRecord);
    }

    @Test
    public void testDeleteRecord() {
        doNothing().when(medicalRecordRepo).deleteById("MR001");

        medicalRecordService.deleteRecord("MR001");

        verify(medicalRecordRepo, times(1)).deleteById("MR001");
    }
}
