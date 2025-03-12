package com.healthhub.authentication.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthhub.authentication.config.JwtGeneratorInterface;
import com.healthhub.authentication.dto.request.LoginRequestDTO;
import com.healthhub.authentication.dto.request.SignupRequestDTO;
import com.healthhub.authentication.dto.response.Message;
import com.healthhub.authentication.model.User;
import com.healthhub.authentication.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final JwtGeneratorInterface jwtGenerator;

    public UserController(UserService userService, JwtGeneratorInterface jwtGenerator) {
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO
    // loginRequestDTO) {

    // try {
    // if(loginRequestDTO.getUserName() == null || loginRequestDTO.getPassword() ==
    // null) {
    // return new ResponseEntity<>(new Message("UserName or Password is
    // Empty"),HttpStatus.CONFLICT);
    // }

    // User userData =
    // userService.getUserByNameAndPassword(loginRequestDTO.getUserName(),
    // loginRequestDTO.getPassword());

    // if(userData == null){
    // return new ResponseEntity<>(new Message("UserName or Password is
    // invalid"),HttpStatus.CONFLICT);
    // }

    // return new ResponseEntity<>(jwtGenerator.generateToken(userData),
    // HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    // }
    // }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            if (loginRequestDTO.getUserName() == null || loginRequestDTO.getPassword() == null) {
                return new ResponseEntity<>(new Message("UserName or Password is Empty"), HttpStatus.CONFLICT);
            }

            User userData = userService.getUserByNameAndPassword(loginRequestDTO.getUserName(),
                    loginRequestDTO.getPassword());

            if (userData == null) {
                return new ResponseEntity<>(new Message("UserName or Password is invalid"), HttpStatus.CONFLICT);
            }

            // Generate the token map
            Map<String, String> tokenMap = jwtGenerator.generateToken(userData);

            // Extract the JWT token
            String token = tokenMap.get("token");

            // Create HttpOnly cookie
            ResponseCookie cookie = ResponseCookie.from("authToken", token)
                    .httpOnly(true)
                    .secure(true) // true in production
                    .path("/") // Ensure cookie is available site-wide
                    .maxAge(24 * 60 * 60) // 1 day expiration
                    .sameSite("None")
                    .build();

            // Set the cookie in the response header
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new Message(tokenMap.get("message"))); // Sending back any message from the map
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

            

    
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        try {
            User existingUser = userService.getUserByName(signupRequestDTO.getUserName());

            if (existingUser != null) {
                Message error = new Message("Username is already taken");
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
            }

            existingUser = userService.getUserByEmail(signupRequestDTO.getEmail());
            if (existingUser != null) {
                Message error = new Message("Email is already registered");
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
            }

            User newUser = new User();
            newUser.setUserName(signupRequestDTO.getUserName());
            newUser.setPassword(signupRequestDTO.getPassword()); // Remember to hash the password before saving
            newUser.setEmail(signupRequestDTO.getEmail());
            newUser.setEnable(false);
            newUser.setRole("Patient");
            userService.saveUser(newUser);

            // Generate a JWT token for the new user
            // String token = jwtGenerator.generateToken(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            Message error = new Message("An error occurred during the signup process");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
