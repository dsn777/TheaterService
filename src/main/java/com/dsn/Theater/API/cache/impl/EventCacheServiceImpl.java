package com.dsn.Theater.API.cache.impl;

import com.dsn.Theater.API.cache.EventCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@Slf4j
public class EventCacheServiceImpl implements EventCacheService {
    @Override
    @CacheEvict(value = EventCacheService.CURRENT_EVENTS_CACHE_NAME)
    public void clear() {
        log.info("{} has been cleared at {}", CURRENT_EVENTS_CACHE_NAME, Instant.now());
    }
}
