package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class UserModel {
    @Id
    private String id;

    private String name;
    private String email;
    private String contacNo;
}
