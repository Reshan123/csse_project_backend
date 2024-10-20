package com.HospitalManagementSystem.HospitalManagementSystem.Auth.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends User{
    private String department;

    public Doctor(String department) {
        this.department = department;
    }

    public Doctor(String username, String email, String password, String department) {
        super(username, email, password);
        this.department = department;
    }
    public Doctor() {
        super();
    }
}

