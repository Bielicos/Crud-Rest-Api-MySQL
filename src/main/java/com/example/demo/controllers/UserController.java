package com.example.demo.controllers;

import com.example.demo.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController("/crud")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
