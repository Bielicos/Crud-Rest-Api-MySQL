package com.example.investhub.entity;

import jakarta.persistence.*;

@Table(name = "account_stock_tb")
@Entity
public class AccountStock {
    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column
    private Integer quantity;
}
