package com.example.investhub.repository;

import com.example.investhub.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository
    extends JpaRepository<Stock, String> {
}
