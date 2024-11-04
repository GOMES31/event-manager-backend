package com.eventmanager.controller;

import com.eventmanager.dto.UserDTO;
import com.eventmanager.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final String USER_SIGNUP = "/signup";

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = USER_SIGNUP, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
        boolean isRegistered = userService.isUserRegistered(userDTO.getEmail());

        if(isRegistered) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already in use!");
        }

        userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

}
