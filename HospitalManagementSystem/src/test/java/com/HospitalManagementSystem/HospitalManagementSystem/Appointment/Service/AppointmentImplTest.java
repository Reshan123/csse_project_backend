package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Service;

import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentImplTest {

    @Mock
    private AppointmentRepository appointmentRepo;

    @InjectMocks
    private AppointmentImpl appointmentService;

    private Appointment testAppointment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // test appointment
        testAppointment = new Appointment();
        testAppointment.setAptNo("APT001");
        testAppointment.setAppointmentDate(new Date());
        testAppointment.setPatientID("PAT001");
        testAppointment.setPatientName("John Doe");
        testAppointment.setDocID("DOC001");
        testAppointment.setDocName("Dr. Smith");
        testAppointment.setReason("General Checkup");
        testAppointment.setDepartment("General Medicine");
        testAppointment.setStatus("Pending");
        testAppointment.setCreatedAt(new Date());
        testAppointment.setUpdatedAt(new Date());
    }

    @Test
    public void testGetAllAppointments() {
        when(appointmentRepo.findAll()).thenReturn(Arrays.asList(testAppointment));

        var result = appointmentService.getAllAppointments();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(appointmentRepo, times(1)).findAll();
    }

    @Test
    public void testGetAppointmentById() {
        when(appointmentRepo.findById("APT001")).thenReturn(Optional.of(testAppointment));

        var result = appointmentService.getAppointmentById("APT001");

        assertTrue(result.isPresent());
        assertEquals("APT001", result.get().getAptNo());
        verify(appointmentRepo, times(1)).findById("APT001");
    }

    @Test
    public void testGetAppointmentById_NotFound() {
        when(appointmentRepo.findById("APT002")).thenReturn(Optional.empty());

        var result = appointmentService.getAppointmentById("APT002");

        assertFalse(result.isPresent());
        verify(appointmentRepo, times(1)).findById("APT002");
    }

    @Test
    public void testGetAllAppointmentsByPatientID() {
        when(appointmentRepo.findAllByPatientID("PAT001")).thenReturn(Arrays.asList(testAppointment));

        var result = appointmentService.getAllAppointmentsByPatientID("PAT001");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("PAT001", result.get(0).getPatientID());
        verify(appointmentRepo, times(1)).findAllByPatientID("PAT001");
    }

    @Test
    public void testAddAppointment() {
        when(appointmentRepo.save(any(Appointment.class))).thenReturn(testAppointment);

        var result = appointmentService.addAppointment(testAppointment);

        assertNotNull(result);
        assertEquals("APT001", result.getAptNo());
        verify(appointmentRepo, times(1)).save(testAppointment);
    }

    @Test
    public void testUpdateAppointment() {
        when(appointmentRepo.findById("APT001")).thenReturn(Optional.of(testAppointment));
        when(appointmentRepo.save(any(Appointment.class))).thenReturn(testAppointment);

        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setAppointmentDate(new Date());
        updatedAppointment.setPatientID("PAT001");
        updatedAppointment.setPatientName("Jane Doe");
        updatedAppointment.setDocID("DOC002");
        updatedAppointment.setDocName("Dr. Johnson");
        updatedAppointment.setReason("Follow-up");
        updatedAppointment.setDepartment("Cardiology");
        updatedAppointment.setStatus("Confirmed");

        var result = appointmentService.updateAppointment("APT001", updatedAppointment);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getPatientName());
        assertEquals("Dr. Johnson", result.getDocName());
        assertEquals("Cardiology", result.getDepartment());
        verify(appointmentRepo, times(1)).findById("APT001");
        verify(appointmentRepo, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testUpdateAppointment_NotFound() {
        when(appointmentRepo.findById("APT002")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            appointmentService.updateAppointment("APT002", testAppointment);
        });

        assertEquals("Appointment not found with appointment number: APT002", exception.getMessage());
        verify(appointmentRepo, times(1)).findById("APT002");
        verify(appointmentRepo, times(0)).save(any(Appointment.class));
    }

    @Test
    public void testDeleteAppointment() {
        appointmentService.deleteAppointment("APT001");

        verify(appointmentRepo, times(1)).deleteById("APT001");
    }
}
