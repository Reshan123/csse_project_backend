package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {

    @Test
    public void testAppointmentConstructorAndGetters() {

        String aptNo = "123";
        Date appointmentDate = new Date();
        String patientID = "P001";
        String patientName = "John Doe";
        String docID = "D001";
        String docName = "Dr. Smith";
        String reason = "Check-up";
        String department = "Cardiology";
        String status = "Pending";
        Date createdAt = new Date();
        Date updatedAt = new Date();

        Appointment appointment = new Appointment();
        appointment.setAptNo(aptNo);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setPatientID(patientID);
        appointment.setPatientName(patientName);
        appointment.setDocID(docID);
        appointment.setDocName(docName);
        appointment.setReason(reason);
        appointment.setDepartment(department);
        appointment.setStatus(status);
        appointment.setCreatedAt(createdAt);
        appointment.setUpdatedAt(updatedAt);

        assertEquals(aptNo, appointment.getAptNo());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals(patientID, appointment.getPatientID());
        assertEquals(patientName, appointment.getPatientName());
        assertEquals(docID, appointment.getDocID());
        assertEquals(docName, appointment.getDocName());
        assertEquals(reason, appointment.getReason());
        assertEquals(department, appointment.getDepartment());
        assertEquals(status, appointment.getStatus());
        assertEquals(createdAt, appointment.getCreatedAt());
        assertEquals(updatedAt, appointment.getUpdatedAt());
    }

    @Test
    public void testAppointmentSetters() {
        Appointment appointment = new Appointment();
        String aptNo = "123";
        Date appointmentDate = new Date();
        String patientID = "P001";
        String patientName = "John Doe";
        String docID = "D001";
        String docName = "Dr. Smith";
        String reason = "Check-up";
        String department = "Cardiology";
        String status = "Pending";
        Date createdAt = new Date();
        Date updatedAt = new Date();

        appointment.setAptNo(aptNo);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setPatientID(patientID);
        appointment.setPatientName(patientName);
        appointment.setDocID(docID);
        appointment.setDocName(docName);
        appointment.setReason(reason);
        appointment.setDepartment(department);
        appointment.setStatus(status);
        appointment.setCreatedAt(createdAt);
        appointment.setUpdatedAt(updatedAt);

        assertEquals(aptNo, appointment.getAptNo());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals(patientID, appointment.getPatientID());
        assertEquals(patientName, appointment.getPatientName());
        assertEquals(docID, appointment.getDocID());
        assertEquals(docName, appointment.getDocName());
        assertEquals(reason, appointment.getReason());
        assertEquals(department, appointment.getDepartment());
        assertEquals(status, appointment.getStatus());
        assertEquals(createdAt, appointment.getCreatedAt());
        assertEquals(updatedAt, appointment.getUpdatedAt());
    }
}
