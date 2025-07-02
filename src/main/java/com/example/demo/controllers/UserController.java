package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/home")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO ) {
        Integer newUserId = userService.createUser(signupDTO);
        var location = URI.create("/crud/signup/" + newUserId.toString());
        return ResponseEntity.created(location).body("ID do usuário : " + newUserId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable String userId) {
        var user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
