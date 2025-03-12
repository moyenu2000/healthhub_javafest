package com.healthhub.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthhub.authentication.model.User;
import com.healthhub.authentication.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }


    public User getUserByNameAndPassword(String userName,String password)
    {
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    public User getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
    
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
