package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/crud")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PutMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO ) {
        Integer newUserId = userService.createUser(signupDTO);
        var location = URI.create("/crud/signup/" + newUserId.toString());
        return ResponseEntity.created(location).body("ID do usuário : " + newUserId);
    }
}
