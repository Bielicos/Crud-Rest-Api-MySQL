package com.example.investhub.entity;

import jakarta.persistence.*;

@Table(name = "accountStock_tb")
@Entity
public class AccountStockEntity {
    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    private AccountEntity account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stockId")
    private stockEntity stock;

    @Column
    private Integer quantity;
}
