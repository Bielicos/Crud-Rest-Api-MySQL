package com.example.investhub.controller;

import com.example.investhub.dto.CreateStockDto;
import com.example.investhub.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDTO) {
        stockService.createStock(createStockDTO);
        return  ResponseEntity.ok().build();
    }
}
