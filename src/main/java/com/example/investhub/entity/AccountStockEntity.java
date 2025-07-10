package com.example.investhub.entity;

import jakarta.persistence.*;

@Table(name = "account_stock_tb")
@Entity
public class AccountStockEntity {
    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private StockEntity stock;

    @Column
    private Integer quantity;
}
