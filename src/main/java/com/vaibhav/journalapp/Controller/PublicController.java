package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.ServiceImp.UserImp;
import com.vaibhav.journalapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {


        if (userService.saveUser(user)) {

            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.badRequest().body("User creation failed");
        }
    }

}
