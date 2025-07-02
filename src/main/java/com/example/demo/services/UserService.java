package com.example.demo.services;

import com.example.demo.controllers.SignupDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser (SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.username());
        user.setEmail(signupDTO.email());
        user.setPassword(signupDTO.password());
        var usuarioSalvo =  userRepository.save(user);
        return usuarioSalvo.getUserId();
    }

    public Optional<User> getUserById (String userId) {
        return userRepository.findById(Integer.parseInt(userId));
    }

    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    public void deleteUserById (String userId) {
        userRepository.deleteById(Integer.parseInt(userId));
    }
}
