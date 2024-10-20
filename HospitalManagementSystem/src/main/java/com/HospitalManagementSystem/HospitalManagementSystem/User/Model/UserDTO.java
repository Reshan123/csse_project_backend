package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

import lombok.Data;

@Data
public class UserDTO {

    private String id;
    private String username;
    private String email;
    private String link;
    public UserDTO() {
    }

    public UserDTO(String id, String username, String email,String link) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
