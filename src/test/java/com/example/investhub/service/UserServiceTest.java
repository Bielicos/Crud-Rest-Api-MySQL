package com.example.investhub.service;

import com.example.investhub.dto.CreateUserDto;
import com.example.investhub.dto.UpdateUserDto;
import com.example.investhub.entity.User;
import com.example.investhub.repository.UserRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userEntityArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;

    @Nested
    class CreateUser {
        @Test
        @DisplayName("Should create a user with success")
        void ShouldCreateUserWithSuccess() {
            //Arrange
            var user = new User(
                    1,
                    "Gabriel",
                    "email@email.com",
                    "senha123",
                    Instant.now(),
                    Instant.now(),
                    Collections.emptyList()
            );

            doReturn(user).when(userRepository).save(userEntityArgumentCaptor.capture());

            var input = new CreateUserDto(
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

            var input = new CreateUserDto(
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
            var user = new User(
                    2,
                    "Juan",
                    "juan@email.com",
                    "senha321",
                    Instant.now(),
                    Instant.now(),
                    Collections.emptyList()
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

    @Nested
    class getAllUsers {
        @Test
        @DisplayName("Should get all users with success")
        void shouldGetAllUsersWithSuccess() {
            // Arrange
            var user = new User(
                    3,
                    "Tamyris",
                    "tamirys@email.com",
                    "senha456",
                    Instant.now(),
                    Instant.now(),
                    Collections.emptyList()
            );
            var listaDeUsuarios = List.of(user);
            doReturn(listaDeUsuarios).when(userRepository).findAll();

            // Act
            var output =  userService.getAllUsers();

            // Arrange
            assertFalse(output.isEmpty(), "Verifica se não retorna algo vazio");
            assertEquals(listaDeUsuarios.size(), output.size(), "Compara se recebeu uma lista de usuários");
        }
    }

    @Nested
    class UpdateUser {
        @Test
        @DisplayName("Should update user with success when username and password is filled")
        void shouldUpdateUserWithSuccessWhenUsernameAndPasswordIsFilled () {
            //Arrange
            var user = new User(
                    1,
                    "Gabriel",
                    "email@email.com",
                    "senha123",
                    Instant.now(),
                    Instant.now(),
                    Collections.emptyList()
            );

            Integer userId = user.getUserId();

            var updateUserDto = new UpdateUserDto(
                    "Alecrim",
                    "123senha"
            );

            // Comportamento que se deve ter
            doReturn(Optional.of(user)).when(userRepository).findById(integerArgumentCaptor.capture());
            doReturn(user).when(userRepository).save(userEntityArgumentCaptor.capture());

            // Act
            userService.updateUserById(userId.toString(), updateUserDto);

            // Assert
            assertEquals(userId, integerArgumentCaptor.getValue(), "Verificando se o argumento passado para a verificação se o usuário existe é o realmente o ID do usuário passado pelo parâmetro do método");
            var userCaptured = userEntityArgumentCaptor.getValue();
            assertEquals(updateUserDto.username(), userCaptured.getUsername(), "Verificando se o username passado para atualizar é o correto");
            assertEquals(updateUserDto.password(), userCaptured.getPassword(), "Verificando se o password passado para atualizar é o correto");

            verify(userRepository, times(1)).findById(integerArgumentCaptor.capture());
            verify(userRepository, times(1)).save(userEntityArgumentCaptor.capture());
        }

        @Test
        @DisplayName("Should not update user because he doesn't exist")
        void shouldNotUpdateUserWhenUserDoesNotExist () {
            //Arrange
            var updateUserDto = new UpdateUserDto(
                    "Gabriel",
                    "senha123"
            );

            Integer userId = 1;

            doReturn(Optional.empty()).when(userRepository).findById(integerArgumentCaptor.capture());

            // Act
            userService.updateUserById(userId.toString(), updateUserDto);

            // Assert
            assertEquals(userId, integerArgumentCaptor.getValue(), "Verificando se o argumento passado para a verificação se o usuário existe é o realmente o ID do usuário passado pelo parâmetro do método");

            verify(userRepository, times(1)).findById(integerArgumentCaptor.capture());
            // Verificar abaixo que o save não é chamado :
            verify(userRepository, times(0)).save(userEntityArgumentCaptor.capture());
        }
    }

    @Nested
    class DeleteUser {
        @Test
        @DisplayName("Should delete user by id with success when user exists")
        void shouldDeleteUserByIdWithSuccessWhenUserExists() {
            // Arrange
            var user = new User(
                    5,
                    "Leandro",
                    "leandro@email.com",
                    "senha654",
                    Instant.now(),
                    Instant.now(),
                    Collections.emptyList()
            );
            var userID = user.getUserId();
            doReturn(true).when(userRepository).existsById(integerArgumentCaptor.capture());
            // Metodo void a gente usa doNothing, pois não retorna nada.
            doNothing().when(userRepository).deleteById(integerArgumentCaptor.capture());

            // Act
            userService.deleteUserById(user.getUserId().toString());

            // Assert
            // Cria uma lista contendo todos os argumentos capturados pelo captor.
            var idList = integerArgumentCaptor.getAllValues();
            assertEquals(userID, idList.get(0), "Checa se o primeiro argumento capturado é o userId");
            assertEquals(userID, idList.get(1), "Checa se o segundo argumento capturado é o userId");

            // Verificando se os metodos são chamados apenas uma vez.
            verify(userRepository, times(1)).existsById(idList.get(0));
            verify(userRepository, times(1)).deleteById(idList.get(0));
        }

        @Test
        @DisplayName("Should not delete user when user don't exists")
        void shouldNotDeleteUserWhenUserDontExists() {
            // Arrange
            Integer userID = 6;
            doReturn(false).when(userRepository).existsById(integerArgumentCaptor.capture());

            // Act
            userService.deleteUserById(userID.toString());

            // Assert
            assertEquals(userID, integerArgumentCaptor.getValue(), "Checa se o argumento capturado é o userId");

            verify(userRepository, times(1)).existsById(integerArgumentCaptor.getValue());
            verify(userRepository, times(0)).deleteById(any());
        }
    }
}