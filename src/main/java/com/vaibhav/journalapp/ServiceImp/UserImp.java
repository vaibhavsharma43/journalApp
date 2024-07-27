package com.vaibhav.journalapp.ServiceImp;


import com.vaibhav.journalapp.Repository.UserRepository;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveUser(User user) {
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
    public boolean deleteById(ObjectId myId) {
        try {
            if (userRepository.existsById(myId)) {
                userRepository.deleteById(myId);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

}
