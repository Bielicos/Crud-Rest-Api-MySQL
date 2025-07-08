package com.example.investhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // Indica pro Hibernate que essa classe vai ser um campo de identificador da nossa entidade.
public class AccountStockId { // Essa classe será uma chave composta entre o accountId e o stockId
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "stock_id")
    private String stockId;
}
