package com.HospitalManagementSystem.HospitalManagementSystem.Hospital.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId; // Import ObjectId if you want to use ObjectId directly

@Document(collection = "hospitals")
public class Hospital {

    @Id
    private String hospitalID;  // MongoDB will automatically generate this ID
    private String name;
    private String location;
    private String contactInfo;

    // Constructors
    public Hospital() {}

    public Hospital(String name, String location, String contactInfo) {
        this.name = name;
        this.location = location;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
