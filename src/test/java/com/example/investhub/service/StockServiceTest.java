package com.example.investhub.service;

import com.example.investhub.dto.CreateStockDto;
import com.example.investhub.dto.CreateUserDto;
import com.example.investhub.entity.Stock;
import com.example.investhub.entity.User;
import com.example.investhub.repository.AccountRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StockService stockService;

    @Captor
    private ArgumentCaptor<Stock> stockArgumentCaptor;

    @Nested
    class CreateStock {

        @Test
        @DisplayName("Deve salvar o estoque quando os dados forem válidos")
        void shouldSaveStockWhenDataIsValid() {
            // Arrange
            var dto = new CreateStockDto("PETR4", "Petrobras");
            var savedStock = new Stock();
            savedStock.setStockId(dto.stockId());
            savedStock.setDescription(dto.description());
            // Simula retorno do repository
            when(stockRepository.save(any(Stock.class))).thenReturn(savedStock);

            // Act
            assertDoesNotThrow(() -> stockService.createStock(dto));

            // Assert
            verify(stockRepository, times(1)).save(any(Stock.class));
        }

    }
}