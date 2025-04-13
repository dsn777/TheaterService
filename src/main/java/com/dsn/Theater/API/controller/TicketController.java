package com.dsn.Theater.API.controller;

import com.dsn.Theater.API.dto.out.TicketDto;
import com.dsn.Theater.API.service.impl.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
@Slf4j
public class TicketController {
    private final CacheManager cacheManager;
    private final TicketServiceImpl ticketService;

    @GetMapping
    public ResponseEntity<?> getTickets(@RequestParam(required = false, defaultValue = "1") Long eventId) {
        List<TicketDto> dtos = ticketService.getAvailableTicketsByEventId(eventId);
        return ResponseEntity.ok(dtos);
    }
}
