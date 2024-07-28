package com.vaibhav.journalapp.Repository;

import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository <User, ObjectId> {
   User findByUserName(String userName);
   void deleteByUserName(String userName);
}
