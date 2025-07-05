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
import java.util.Optional;

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

    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;

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

    @Nested
    class GetUserById {

        @Test
        @DisplayName("Should get user by id with success when optional is present")
        void shouldGetUserByIdWithSuccessWhenOptionalIsPresent() {
            // Arrange
            var user = new UserEntity(
                    2,
                    "Juan",
                    "juan@email.com",
                    "senha321",
                    Instant.now(),
                    Instant.now()
            );
            // quando findById for chamado com qualquer Integer, retorna Optional.of(user)
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(integerArgumentCaptor.capture());

            // Act
            var output = userService.getUserById(user.getUserId().toString());

            // Assert
            assertTrue(output.isPresent(), "Esperava Optional contendo usuário");
            // Verifica que o ID passado ao repository foi o mesmo do nosso user
            assertEquals(user.getUserId(), integerArgumentCaptor.getValue(),
                    "O ID capturado deve ser igual ao ID do usuário");
            // Verifica que o objeto retornado é exatamente o mesmo que criamos
            assertEquals(user, output.get(),
                    "O objeto dentro do Optional deve ser o mesmo user criado");
        }

        @Test
        @DisplayName("Should get user by id with success when optional is empty")
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty() {
            // Arrange
            Integer userId = 3;
            doReturn(Optional.empty()).when(userRepository).findById(integerArgumentCaptor.capture());

            // Act
            var output = userService.getUserById(userId.toString());

            // Arrange
            assertTrue(output.isEmpty(), "Esperava Optional vazio");
            assertEquals(userId, integerArgumentCaptor.getValue(), "Comparar se o ID capturado é o mesmo ID que não existe");
        }


    }
}