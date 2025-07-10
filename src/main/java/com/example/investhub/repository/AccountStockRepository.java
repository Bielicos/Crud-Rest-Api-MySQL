package com.example.investhub.repository;

import com.example.investhub.entity.AccountStock;
import com.example.investhub.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStockRepository
    extends JpaRepository<AccountStock, AccountStockId> {
}
