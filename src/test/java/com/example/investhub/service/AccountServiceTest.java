package com.example.investhub.service;

import com.example.investhub.client.BrapiClient;
import com.example.investhub.repository.AccountRepository;
import com.example.investhub.repository.AccountStockRepository;
import com.example.investhub.repository.StockRepository;
import com.example.investhub.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock private AccountRepository accountRepository;
    @Mock private StockRepository stockRepository;
    @Mock private UserRepository userRepository;
    @Mock private AccountStockRepository accountStockRepository;
    @Mock private BrapiClient brapiClient;

    @InjectMocks
    private AccountService service;

    @Nested
    class CreateAccountTests {

        @Test
        void success() {
            // TODO: implementar cenário de sucesso para createAccount
        }

        @Test
        void failure() {
            // TODO: implementar cenário de falha para createAccount
        }
    }

    @Nested
    class GetAllAccountsFromUserTests {

        @Test
        void success() {
            // TODO: implementar cenário de sucesso para getAllAccountsFromUser
        }

        @Test
        void failure() {
            // TODO: implementar cenário de falha para getAllAccountsFromUser
        }
    }

    @Nested
    class AssociateStockTests {

        @Test
        void success() {
            // TODO: implementar cenário de sucesso para associateStock
        }

        @Test
        void failure() {
            // TODO: implementar cenário de falha para associateStock
        }
    }

    @Nested
    class ListStocksTests {

        @Test
        void success() {
            // TODO: implementar cenário de sucesso para listStocks
        }

        @Test
        void failure() {
            // TODO: implementar cenário de falha para listStocks
        }
    }


}