package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
       List<User> all= userService.getAll();
       if(!all.isEmpty()){
           return ResponseEntity.ok(all);
       }else{
           return ResponseEntity.noContent().build();
       }
    }
}
