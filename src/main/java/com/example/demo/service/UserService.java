package com.example.demo.service;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.UpdateUserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser (CreateUserDTO createUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDTO.username());
        userEntity.setEmail(createUserDTO.email());
        userEntity.setPassword(createUserDTO.password());
        var savedUser =  userRepository.save(userEntity);
        return savedUser.getUserId();
    }

    public Optional<UserEntity> getUserById (String userId) {
        Integer id = Integer.parseInt(userId);
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers () {
        return userRepository.findAll();
    }

    public void deleteUserById (String userId) {
        var id = Integer.parseInt(userId);
        var userExists = userRepository.existsById(id);
        if (userExists) {
            userRepository.deleteById(id);
        }
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
