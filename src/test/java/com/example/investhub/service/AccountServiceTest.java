package com.example.investhub.service;

import com.example.investhub.client.BrapiClient;
import com.example.investhub.dto.*;
import com.example.investhub.entity.Account;
import com.example.investhub.entity.AccountStock;
import com.example.investhub.entity.Stock;
import com.example.investhub.entity.User;
import com.example.investhub.repository.AccountRepository;
import com.example.investhub.repository.AccountStockRepository;
import com.example.investhub.repository.StockRepository;
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
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountStockRepository accountStockRepository;

    @Mock
    private BrapiClient brapiClient;

    @InjectMocks
    private AccountService accountService;

    @Captor
    private ArgumentCaptor<Account> accountCaptor;

    @Captor
    private ArgumentCaptor<Integer> intCaptor;

    @Captor
    private ArgumentCaptor<AccountStock> accountStockCaptor;

    @Nested
    class CreateAccount {

        @Test
        @DisplayName("Should create account with success")
        void shouldCreateAccountWithSuccess() {
            // Arrange
            var user = new User(1, "Nome", "email@teste.com", "senha", Instant.now(), Instant.now(), Collections.emptyList());
            doReturn(Optional.of(user)).when(userRepository).findById(intCaptor.capture());
            doReturn(new Account()).when(accountRepository).save(accountCaptor.capture());

            var dto = new CreateAccountDto("Desc", "Rua X", 123);

            // Act
            accountService.createAccount(user.getUserId().toString(), dto);

            // Assert
            // Validar intCaptor.getValue() == userId, e accountCaptor.getValue() populado corretamente
        }

        @Test
        @DisplayName("Should throw NOT_FOUND when user not exists")
        void shouldThrowWhenUserNotFound() {
            // Arrange
            doReturn(Optional.empty()).when(userRepository).findById(any());

            var dto = new CreateAccountDto("Desc", "Rua X", 123);

            // Act & Assert
            assertThrows(ResponseStatusException.class,
                    () -> accountService.createAccount("42", dto),
                    "Expected NOT_FOUND when user does not exist");
        }
    }

    @Nested
    class GetAllAccountsFromUser {

        @Test
        @DisplayName("Should return list when user has accounts")
        void shouldReturnAccountsWhenPresent() {
            // Arrange
            var account = new Account();
            account.setAccountId(10);
            account.setDescription("Desc");
            var user = new User(1, "Nome", "email@teste.com", "senha", Instant.now(), Instant.now(), Collections.singletonList(account));
            doReturn(Optional.of(user)).when(userRepository).findById(intCaptor.capture());

            // Act
            List<AccountResponseDto> result = accountService.getAllAccountsFromUser(user.getUserId().toString());

            // Assert
            // validações de result
        }

        @Test
        @DisplayName("Should throw NOT_FOUND when user not exists")
        void shouldThrowWhenUserNotFound() {
            // Arrange
            doReturn(Optional.empty()).when(userRepository).findById(any());

            // Act & Assert
            assertThrows(ResponseStatusException.class,
                    () -> accountService.getAllAccountsFromUser("99"));
        }
    }

    @Nested
    class AssociateStock {

        @Test
        @DisplayName("Should associate stock with success")
        void shouldAssociateStockWithSuccess() {
            // Arrange
            var account = new Account();
            account.setAccountId(5);
            var stock = new Stock();
            stock.setStockId("ABC");
            doReturn(Optional.of(account)).when(accountRepository).findById(intCaptor.capture());
            doReturn(Optional.of(stock)).when(stockRepository).findById(any());
            doReturn(new AccountStock()).when(accountStockRepository).save(accountStockCaptor.capture());

            var dto = new AssociateAccountStockDto("ABC", 3);

            var id = Integer.toString(account.getAccountId());

            // Act
            accountService.associateStock(id, dto);

            // Assert
            // validar captors
        }

        @Test
        @DisplayName("Should throw NOT_FOUND when account not exists")
        void shouldThrowWhenAccountNotFound() {
            // Arrange
            doReturn(Optional.empty()).when(accountRepository).findById(any());

            var dto = new AssociateAccountStockDto("XYZ", 2);

            // Act & Assert
            assertThrows(ResponseStatusException.class,
                    () -> accountService.associateStock("1", dto));
        }

        @Test
        @DisplayName("Should throw NOT_FOUND when stock not exists")
        void shouldThrowWhenStockNotFound() {
            // Arrange
            var account = new Account();
            account.setAccountId(5);
            doReturn(Optional.of(account)).when(accountRepository).findById(any());
            doReturn(Optional.empty()).when(stockRepository).findById(any());

            var dto = new AssociateAccountStockDto("NOPE", 1);

            // Act & Assert
            assertThrows(ResponseStatusException.class,
                    () -> accountService.associateStock("5", dto));
        }
    }

}
