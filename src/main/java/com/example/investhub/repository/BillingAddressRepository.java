package com.example.investhub.repository;

import com.example.investhub.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository
    extends JpaRepository<BillingAddress, Integer> {
}
