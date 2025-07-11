package com.example.investhub.controller;

import com.example.investhub.dto.CreateAccountDTO;
import com.example.investhub.dto.CreateStockDTO;
import com.example.investhub.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {
        stockService.createStock(createStockDTO);
        return  ResponseEntity.ok().build();
    }
}
