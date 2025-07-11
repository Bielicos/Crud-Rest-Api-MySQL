package com.example.investhub.service;

import com.example.investhub.dto.CreateStockDto;
import com.example.investhub.entity.Stock;
import com.example.investhub.repository.AccountRepository;
import com.example.investhub.repository.StockRepository;
import com.example.investhub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private UserRepository userRepository;

    public StockService(AccountRepository accountRepository, StockRepository stockRepository,  UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    public void createStock(CreateStockDto createStockDTO) {
        var stock = new Stock();
        stock.setStockId(createStockDTO.stockId());
        stock.setDescription(createStockDTO.description());

        stockRepository.save(stock);
    }
}
