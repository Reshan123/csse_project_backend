package com.HospitalManagementSystem.HospitalManagementSystem.Appointment.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "appointments")
@Data
public class Appointment {
    @Id
    private String aptNo;

    private Date appointmentDate;

    private String patientID;

    private String patientName;

    private String docID;

    private String docName;

    private String reason;

    private String department;

    private String status;

    private Date createdAt;

    private Date updatedAt;

    // Setter for appointmentDate with validation
    public void setAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must be a future date");
        }
        this.appointmentDate = appointmentDate;
    }

    // Setter for patientID with validation
    public void setPatientID(String patientID) {
        if (patientID == null || patientID.isEmpty()) {
            throw new IllegalArgumentException("Patient ID is mandatory");
        }
        this.patientID = patientID;
    }

    // Setter for patientName with validation
    public void setPatientName(String patientName) {
        if (patientName == null || patientName.isEmpty()) {
            throw new IllegalArgumentException("Patient name is mandatory");
        }
        this.patientName = patientName;
    }

    // Setter for docID with validation
    public void setDocID(String docID) {
        if (docID == null || docID.isEmpty()) {
            throw new IllegalArgumentException("Doctor ID is mandatory");
        }
        this.docID = docID;
    }

    // Setter for docName with validation
    public void setDocName(String docName) {
        if (docName == null || docName.isEmpty()) {
            throw new IllegalArgumentException("Doctor name is mandatory");
        }
        this.docName = docName;
    }

    // Setter for reason with validation
    public void setReason(String reason) {
        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Reason for appointment is mandatory");
        }
        this.reason = reason;
    }

    // Setter for department with validation
    public void setDepartment(String department) {
        if (department == null || department.isEmpty()) {
            throw new IllegalArgumentException("Department is mandatory");
        }
        this.department = department;
    }

    // Setter for status with validation
    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status is mandatory");
        }
        this.status = status;
    }

    // Optionally, you can add logic to handle createdAt and updatedAt timestamps
    public void setCreatedAt(Date createdAt) {
        if (createdAt == null) {
            this.createdAt = new Date(); // Set to current date if null
        } else {
            this.createdAt = createdAt;
        }
    }

    public void setUpdatedAt(Date updatedAt) {
        if (updatedAt == null) {
            this.updatedAt = new Date(); // Set to current date if null
        } else {
            this.updatedAt = updatedAt;
        }
    }
}
