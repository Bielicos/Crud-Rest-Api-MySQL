package com.example.investhub.service;

import com.example.investhub.dto.CreateStockDTO;
import com.example.investhub.entity.Stock;
import com.example.investhub.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDTO createStockDTO) {
        var stock = new Stock();
        stock.setStockId(createStockDTO.stockId());
        stock.setDescription(createStockDTO.description());

        stockRepository.save(stock);
    }
}
