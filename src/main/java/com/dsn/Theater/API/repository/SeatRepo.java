package com.dsn.Theater.API.repository;

import com.dsn.Theater.API.entity.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepo extends CrudRepository<Seat, Long> {
}
