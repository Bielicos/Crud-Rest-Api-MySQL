package com.example.demo.controller;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.UpdateUserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody CreateUserDTO createUserDTO) {
        Integer newUserId = userService.createUser(createUserDTO);
        var location = URI.create("/crud/signup/" + newUserId.toString());
        return ResponseEntity.created(location).body("ID do usuário : " + newUserId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String userId) {
        var user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> getUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable String userId, @RequestBody UpdateUserDTO updateUserDTO ) {
        userService.updateUserById(userId, updateUserDTO);
        return ResponseEntity.noContent().build();
    }
}
