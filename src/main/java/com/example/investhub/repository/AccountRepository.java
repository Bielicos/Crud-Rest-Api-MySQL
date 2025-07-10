package com.example.investhub.repository;

import com.example.investhub.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
    extends JpaRepository<Account, Integer> {
}
