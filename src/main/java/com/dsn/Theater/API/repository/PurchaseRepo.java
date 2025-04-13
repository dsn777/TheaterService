package com.dsn.Theater.API.repository;

import com.dsn.Theater.API.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PurchaseRepo extends CrudRepository<Purchase, UUID> {
}
