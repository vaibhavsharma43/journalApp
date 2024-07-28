package com.vaibhav.journalapp.Service;


import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    boolean saveUser(User user);
    boolean saveExistUser(User user);
    List<User> getAll();

    User findById(ObjectId myId);

    void deleteByUserName(String userName);

    User findByUsername(String username);
}
