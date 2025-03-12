package com.healthhub.authentication.config;

import java.util.Map;

import com.healthhub.authentication.model.User;

public interface JwtGeneratorInterface {

    Map<String, String> generateToken(User user);
}
