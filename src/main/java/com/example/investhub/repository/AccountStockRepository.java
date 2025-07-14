package com.example.investhub.repository;

import com.example.investhub.entity.AccountStock;
import com.example.investhub.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository
    extends JpaRepository<AccountStock, AccountStockId> {
}
