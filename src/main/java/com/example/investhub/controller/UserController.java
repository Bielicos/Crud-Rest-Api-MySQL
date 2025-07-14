package com.example.investhub.controller;

import com.example.investhub.dto.CreateUserDto;
import com.example.investhub.dto.UpdateUserDto;
import com.example.investhub.dto.UserResponseDto;
import com.example.investhub.entity.User;
import com.example.investhub.repository.UserRepository;
import com.example.investhub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v2/users")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody CreateUserDto createUserDTO) {
        Integer newUserId = userService.createUser(createUserDTO);
        var location = URI.create("/crud/signup/" + newUserId.toString());
        return ResponseEntity.created(location).body("ID do usuário : " + newUserId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDTO ) {
        userService.updateUserById(userId, updateUserDTO);
        return ResponseEntity.noContent().build();
    }
}
