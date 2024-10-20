package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model.Appointment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppointmentNegativeTest {

    // Negative tests for setter methods

    @Test
    public void testSetAppointmentDate_Null() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(null);
        }, "Appointment date must be a future date");
    }

    @Test
    public void testSetAppointmentDate_PastDate() {
        Appointment appointment = new Appointment();
        Date pastDate = new Date(System.currentTimeMillis() - 86400000); // 1 day in the past
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(pastDate);
        }, "Appointment date must be a future date");
    }

    @Test
    public void testSetPatientID_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setPatientID("");
        }, "Patient ID is mandatory");
    }

    @Test
    public void testSetPatientName_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setPatientName("");
        }, "Patient name is mandatory");
    }

    @Test
    public void testSetDocID_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDocID("");
        }, "Doctor ID is mandatory");
    }

    @Test
    public void testSetDocName_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDocName(null);
        }, "Doctor name is mandatory");
    }

    @Test
    public void testSetReason_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setReason("");
        }, "Reason for appointment is mandatory");
    }

    @Test
    public void testSetDepartment_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDepartment("");
        }, "Department is mandatory");
    }

    @Test
    public void testSetStatus_Empty() {
        Appointment appointment = new Appointment();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setStatus("");
        }, "Status is mandatory");
    }
}
