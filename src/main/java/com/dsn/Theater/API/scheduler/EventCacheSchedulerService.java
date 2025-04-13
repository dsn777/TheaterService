package com.dsn.Theater.API.scheduler;

import com.dsn.Theater.API.cache.EventCacheService;
import com.dsn.Theater.API.service.interfaces.EventService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventCacheSchedulerService {
    private final CacheManager cacheManager;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final EventService eventService;
    private final EventCacheService eventCacheService;

    private void scheduleCacheUpdating() {
        eventCacheService.clear();
        var eventDtos = eventService.getCurrentEvents();
        if (eventDtos.isEmpty()) {
            return;
        }
        var nearestEventDto = eventDtos.getFirst();
        var nearestEventDate = nearestEventDto.getDate();
        Instant nearestEventInstant = nearestEventDate.atZone(ZoneId.of("Europe/Moscow")).toInstant();
        taskScheduler.schedule(this::scheduleCacheUpdating, nearestEventInstant);
        log.info("{} cache clearing is scheduled for {}", EventCacheService.CURRENT_EVENTS_CACHE_NAME, nearestEventInstant);
    }

    @PostConstruct
    private void scheduleInit() {
        Instant initDelay = Instant.now().plusSeconds(10);
        log.info("{} cache update planning will be scheduled at {}", EventCacheService.CURRENT_EVENTS_CACHE_NAME, initDelay);
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(this::scheduleCacheUpdating, initDelay);
    }
}
