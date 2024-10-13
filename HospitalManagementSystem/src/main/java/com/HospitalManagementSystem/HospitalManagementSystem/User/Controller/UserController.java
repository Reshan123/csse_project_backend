package com.HospitalManagementSystem.HospitalManagementSystem.User.Controller;


import com.HospitalManagementSystem.HospitalManagementSystem.User.Model.UserModel;
import com.HospitalManagementSystem.HospitalManagementSystem.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/UserModels")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserModel> getRecordById(@PathVariable String id) {
        Optional<UserModel> record = userService.getUserById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/addRecord")
    public UserModel createRecord(@RequestBody UserModel user){
        return userService.addUser(user);
    }

    @PutMapping("/updateRecord/{id}")
    public ResponseEntity<UserModel> updateUserModel(@PathVariable String id,@RequestBody UserModel user){
        UserModel updatedRecord = userService.updateUser(id,user);
        if (updatedRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserModel(String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
