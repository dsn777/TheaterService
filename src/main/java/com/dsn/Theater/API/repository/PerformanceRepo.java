package com.dsn.Theater.API.repository;

import com.dsn.Theater.API.entity.Performance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerformanceRepo extends CrudRepository<Performance, Long> {
    List<Performance> findAll();
}
