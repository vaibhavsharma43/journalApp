package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.saveUser(user)) {

            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.badRequest().body("User creation failed");
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName) {
        User userInDb = userService.findByUsername(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.badRequest().body("User update failed");
        }

    }


}


