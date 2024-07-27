package com.vaibhav.journalapp.Service;


import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    boolean saveUser(User user);

    List<User> getAll();

    User findById(ObjectId myId);

    boolean deleteById(ObjectId myId);

    User findByUsername(String username);
}
