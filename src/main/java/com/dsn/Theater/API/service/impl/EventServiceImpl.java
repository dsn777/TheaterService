package com.dsn.Theater.API.service.impl;

import com.dsn.Theater.API.cache.EventCacheService;
import com.dsn.Theater.API.dto.out.EventDto;
import com.dsn.Theater.API.mapper.EventMapper;
import com.dsn.Theater.API.repository.EventRepo;
import com.dsn.Theater.API.scheduler.EventCacheSchedulerService;
import com.dsn.Theater.API.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepo eventRepo;
    private final EventMapper eventMapper;

    @Cacheable(value = "ALL_EVENTS")
    public List<EventDto> getAllEvents() {
        var events = eventRepo.findAll();
        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(EventCacheService.CURRENT_EVENTS_CACHE_NAME)
    public List<EventDto> getCurrentEvents() {
        LocalDateTime now = LocalDateTime.now();
        var events = eventRepo.findCurrentEvents(now);

        return events.stream()
                .map(eventMapper::toDto)
                .toList();
    }
    @Override
    public void createEvent() {
    }

    @Override
    public void createPresetEvent() {
    }
}
