package com.example.investhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts_tb")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "description")
    private String description;

    // Relacionamentos

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "account_id")
    private BillingAddress billingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account")
    public List<AccountStock> accountStocks;
}
