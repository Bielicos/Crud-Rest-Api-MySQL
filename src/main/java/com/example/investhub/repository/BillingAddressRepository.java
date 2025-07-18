package com.example.investhub.repository;

import com.example.investhub.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository
    extends JpaRepository<BillingAddress, Integer> {
}
