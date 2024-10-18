package com.HospitalManagementSystem.HospitalManagementSystem.Auth.payload.response;
public class ValidationResponse {
    private boolean valid;

    public ValidationResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}