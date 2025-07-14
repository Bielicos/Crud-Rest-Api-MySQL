package com.example.investhub.service;

import com.example.investhub.dto.CreateUserDto;
import com.example.investhub.dto.UpdateUserDto;
import com.example.investhub.dto.UserResponseDto;
import com.example.investhub.entity.User;
import com.example.investhub.repository.AccountRepository;
import com.example.investhub.repository.BillingAddressRepository;
import com.example.investhub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public Integer createUser (CreateUserDto createUserDTO) {
        User userEntity = new User();
        userEntity.setUsername(createUserDTO.username());
        userEntity.setEmail(createUserDTO.email());
        userEntity.setPassword(createUserDTO.password());
        var savedUser =  userRepository.save(userEntity);
        return savedUser.getUserId();
    }

    public Optional<User> getUserById (String userId) {
        Integer id = Integer.parseInt(userId);
        return userRepository.findById(id);
    }

    public List<UserResponseDto> getAllUsers () {
        var allUsers = userRepository.findAll()
                .stream()
                .map(us -> {
                    return new UserResponseDto(us.getUserId(), us.getUsername(), us.getEmail(), us.getPassword());
                })
                .toList();
        return allUsers;
    }

    public void deleteUserById (String userId) {
        var id = Integer.parseInt(userId);
        var userExists = userRepository.existsById(id);
        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    public void updateUserById (String userId, UpdateUserDto updateUserDTO) {
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
