package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Repository.UserRepository;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;



    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUsername(userName);

        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveUser(userInDb);
        return ResponseEntity.ok("User updated successfully");
    }


    @DeleteMapping()
    public ResponseEntity<?> deleteUserByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return ResponseEntity.ok("User deleted successfully");
    }

}





