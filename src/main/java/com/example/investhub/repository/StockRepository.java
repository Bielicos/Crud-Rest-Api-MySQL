package com.example.investhub.repository;

import com.example.investhub.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository
    extends JpaRepository<Stock, String> {
}
