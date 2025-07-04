package com.example.demo.service;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityArgumentCaptor;

    @Nested
    class CreateUser {
        @Test
        @DisplayName("Should create a user with success")
        void ShouldCreateUserWithSuccess() {
            //Arrange
            var user = new UserEntity(
                    1,
                    "Gabriel",
                    "email@email.com",
                    "senha123",
                    Instant.now(),
                    Instant.now()
            );

            doReturn(user).when(userRepository).save(userEntityArgumentCaptor.capture());

            var input = new CreateUserDTO(
                    "Gabriel",
                    "email@email.com",
                    "senha123"
            );

            //Act
            var output = userService.createUser(input);
            //Assert
            assertNotNull(output);

            var userCaptured =  userEntityArgumentCaptor.getValue();
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void ShouldThrowExceptionWhenErrorOccurs() {
            //Arrange
            doThrow(new RuntimeException()).when(userRepository).save(any());

            var input = new CreateUserDTO(
                    "Gabriel",
                    "teste@email.com",
                    "senha123"
            );

            //Act $ Assert
            assertThrows(RuntimeException.class, () -> userService.createUser(input));
        }
    }
}