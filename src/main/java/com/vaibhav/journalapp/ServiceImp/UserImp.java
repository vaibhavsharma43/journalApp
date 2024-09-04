package com.vaibhav.journalapp.ServiceImp;


import com.vaibhav.journalapp.Repository.UserRepository;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean saveUser(User user) {
        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            List<String> roles = new ArrayList<>();
            roles.add("ADMIN");
roles.add("USER");
            user.setRoles(roles);
            userRepository.save(user);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean saveExistUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public User findById(ObjectId myId) {
        return userRepository.findById(myId).orElse(null);
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }


    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

}
