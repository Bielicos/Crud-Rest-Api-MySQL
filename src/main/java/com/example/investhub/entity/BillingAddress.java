package com.example.investhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "billing_address_tb")
@Entity
public class BillingAddress {
    @Id
    @Column(name = "account_id")
    private int accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId // O que qualifica o identificador acima é a entidade abaixo "Account". Logo, essa anotation garante que o primary key da entidade "Account" seja a coluna "accountId" acima.
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private String street;

    @Column
    private Integer number;

}
