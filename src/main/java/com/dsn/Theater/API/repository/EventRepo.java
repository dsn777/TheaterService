package com.dsn.Theater.API.repository;

import com.dsn.Theater.API.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepo extends CrudRepository<Event, Long> {
    List<Event> findAll();
    @Query(value = "SELECT * FROM Event WHERE date > :now", nativeQuery = true)
    List<Event> findCurrentEvents(@Param("now") LocalDateTime now);
}
