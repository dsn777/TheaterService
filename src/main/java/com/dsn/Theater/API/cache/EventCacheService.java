package com.dsn.Theater.API.cache;

public interface EventCacheService {
    String CURRENT_EVENTS_CACHE_NAME = "CURRENT_EVENTS_CACHE";
    void clear();
    void remove(Long id);
}
