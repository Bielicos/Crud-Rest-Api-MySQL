package com.example.investhub.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @CreationTimestamp
    @Column(name = "creation_time_stamp", nullable = false, updatable = false)
    private Instant creationTimeStamp;

    @UpdateTimestamp
    @Column(name = "modification_time_stamp", nullable = false)
    private Instant modificationTimeStamp;

    // Relacionamento

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
