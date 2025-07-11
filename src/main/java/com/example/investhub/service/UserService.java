package com.example.investhub.service;

import com.example.investhub.dto.CreateAccountDTO;
import com.example.investhub.dto.CreateUserDTO;
import com.example.investhub.dto.UpdateUserDTO;
import com.example.investhub.entity.Account;
import com.example.investhub.entity.BillingAddress;
import com.example.investhub.entity.User;
import com.example.investhub.repository.AccountRepository;
import com.example.investhub.repository.BillingAddressRepository;
import com.example.investhub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    public Integer createUser (CreateUserDTO createUserDTO) {
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

    public List<User> getAllUsers () {
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

    public void createAccount (String userId, CreateAccountDTO createAccountDTO) {
        Integer id = Integer.parseInt(userId);
        var userEntity = userRepository.findById(id).orElseThrow(
                () -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                }
        );

        // DTO -> Entity
        var account = new Account();
        account.setUser(userEntity);
        account.setDescription(createAccountDTO.description());

        var billingAddress = new BillingAddress();
        billingAddress.setStreet(createAccountDTO.street());
        billingAddress.setNumber(createAccountDTO.number());

        // Relacionamento
        billingAddress.setAccount(account);
        account.setBillingAddress(billingAddress); // BillingAdress vai ser salvo ao entrar no cascade.

        var accountCreated = accountRepository.save(account);
    }
}
