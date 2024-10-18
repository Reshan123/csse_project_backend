package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

public class DoctorDTO extends UserDTO {

    private String specialization;
    private String licenseNumber;

    public DoctorDTO() {
    }

    public DoctorDTO(String id, String username, String email, String specialization, String licenseNumber) {
        super(id, username, email);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
