package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;
public class StaffDTO extends UserDTO {

    private String position;
    private String department;

    public StaffDTO() {
    }

    public StaffDTO(String id, String username, String email, String position, String department) {
        super(id, username, email);  // Inherit fields from UserDTO
        this.position = position;
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
