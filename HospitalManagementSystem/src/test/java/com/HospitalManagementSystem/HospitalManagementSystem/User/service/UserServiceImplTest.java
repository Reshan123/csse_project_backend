package com.HospitalManagementSystem.HospitalManagementSystem.User.service;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.ERole;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository.RoleRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.repository.UserRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Model.MedicalRecord;
import com.HospitalManagementSystem.HospitalManagementSystem.MedicalRecord.Repository.MedicalRecordRepository;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.PatientDTO;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private RoleRepository roleRepository;

    private User testUser;
    private MedicalRecord testMedicalRecord;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a test user and medical record
        testUser = new User();
        testUser.setId("1");
        testUser.setUsername("testUser");
        testUser.setEmail("test@example.com");
        List<String> allergies = Arrays.asList("Peanuts", "Dust");
        List<String> medications = Arrays.asList("Medication A", "Medication B");
        testMedicalRecord = new MedicalRecord("MR001",
                "USER123",
                "PATIENT001",
                "John",
                "Doe",
                new Date(),
                "Male",
                "555-1234",
                "123 Street Name",
                allergies,
                medications,
                "Jane Doe",
                "555-5678");
        // Initialize properties for testMedicalRecord if needed
    }

    @Test
    public void testGetPatientById_Success() {
        // Mock repository behavior
        when(userRepository.findById("1")).thenReturn(Optional.of(testUser));
        when(medicalRecordRepository.findByUserId("1")).thenReturn(testMedicalRecord);

        // Act
        PatientDTO result = userService.getPatientById("1");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals(testMedicalRecord, result.getMedicalrecord());
    }

    @Test
    public void testGetPatientById_UserNotFound() {
        // Mock repository behavior for user not found
        when(userRepository.findById("2")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.getPatientById("2");
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testFindByRoles_Success() {
        // Mock role repository behavior
        Role role = new Role();
        role.setName(ERole.ROLE_USER);

        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(role));
        when(userRepository.findByRoles(role)).thenReturn(Collections.singletonList(testUser));

        // Act
        List<User> result = userService.findByRoles(role);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testUser, result.get(0));
    }

    @Test
    public void testFindByRoles_RoleNotFound() {
        // Mock role repository behavior for role not found
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.findByRoles(null);
        });
        assertEquals("Error: Role is not found.", exception.getMessage());
    }
}
