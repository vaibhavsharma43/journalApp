package com.vaibhav.journalapp.Controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.ServiceImp.UserDetailsServiceImp;
import com.vaibhav.journalapp.ServiceImp.UserImp;
import com.vaibhav.journalapp.entity.User;
import com.vaibhav.journalapp.utilis.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {

System.out.print("xx");
        if (userService.saveUser(user)) {

            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.badRequest().body("User creation failed");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {


        if (userService.saveUser(user)) {

            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.badRequest().body("User creation failed");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
       UserDetails userDetails= userDetailsServiceImp.loadUserByUsername(user.getUserName());
String jwt =jwtUtil.generateToken(userDetails.getUsername());
return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }


    }

}
