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
public class UserEntity {
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
    private Instant creationTimeStamp;

    @UpdateTimestamp
    private Instant modificationTimeStamp;

    // Relacionamento

    @OneToMany(mappedBy = "user")
    private List<AccountEntity> accounts;
}
