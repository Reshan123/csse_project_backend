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
}
