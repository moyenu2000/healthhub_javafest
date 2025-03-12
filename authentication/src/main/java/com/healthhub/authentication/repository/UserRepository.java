package com.healthhub.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.healthhub.authentication.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserNameAndPassword(String userName, String password);
    User findByUserName(String userName);
    User findByEmail(String email);
}
