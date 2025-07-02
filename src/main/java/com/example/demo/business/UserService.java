package com.example.demo.business;

import com.example.demo.controller.SignupDTO;
import com.example.demo.controller.UpdateUserDTO;
import com.example.demo.infrastructure.entitys.UserEntity;
import com.example.demo.infrastructure.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupDTO.username());
        userEntity.setEmail(signupDTO.email());
        userEntity.setPassword(signupDTO.password());
        var usuarioSalvo =  userRepository.save(userEntity);
        return usuarioSalvo.getUserId();
    }

    public Optional<UserEntity> getUserById (String userId) {
        Integer id = Integer.parseInt(userId);
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers () {
        return userRepository.findAll();
    }

    public void deleteUserById (String userId) {
        userRepository.deleteById(Integer.parseInt(userId));
    }

    public void updateUserById (String userId, UpdateUserDTO updateUserDTO) {
        Integer id = Integer.parseInt(userId);
        var UserEntity =  userRepository.findById(id);
        if(UserEntity.isPresent()) {
            var user =  UserEntity.get();
            if(updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }
            if(updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }
            userRepository.save(user);
        }
    }
}
