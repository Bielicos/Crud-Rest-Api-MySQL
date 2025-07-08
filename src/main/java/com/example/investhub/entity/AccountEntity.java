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
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private int accountId;

    @Column(name = "description")
    private String description;

    // Relacionamentos

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn()
    private BillingAdressEntity billingAddress;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @OneToMany(mappedBy = "account")
    public List<AccountEntity> accounts;
}
