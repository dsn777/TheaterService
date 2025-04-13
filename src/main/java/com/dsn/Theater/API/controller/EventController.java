package com.dsn.Theater.API.controller;

import com.dsn.Theater.API.dto.out.EventDto;
import com.dsn.Theater.API.scheduler.EventCacheSchedulerService;
import com.dsn.Theater.API.service.impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventServiceImpl eventService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getCurrentEvents() {
        return eventService.getCurrentEvents();
    }
}
