package com.example.demo.services;

import com.example.demo.controllers.SignupDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
}
