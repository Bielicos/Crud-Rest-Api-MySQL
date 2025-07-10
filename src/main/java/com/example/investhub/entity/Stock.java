package com.example.investhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "stock_tb")
@Entity
public class Stock {
    @Id
    @Column(name = "stock_id")
    private String stockId;

    @Column(name = "description")
    private String description;
}
