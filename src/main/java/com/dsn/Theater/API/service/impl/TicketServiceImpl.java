package com.dsn.Theater.API.service.impl;

import com.dsn.Theater.API.dto.out.TicketDto;
import com.dsn.Theater.API.mapper.TicketMapper;
import com.dsn.Theater.API.repository.EventRepo;
import com.dsn.Theater.API.repository.TicketRepo;
import com.dsn.Theater.API.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketMapper ticketMapper;
    private final TicketRepo ticketRepo;
    private final EventRepo eventRepo;
    private final CacheManager cacheManager;

    @Override
    public void setTicketGroup() {
    }

    @Override
    public void checkAvailableTickets() {
    }

    @Override
    @Cacheable(key = "#eventId", value = "ticketDtos", unless = "#result.isEmpty()")
    public List<TicketDto> getAvailableTicketsByEventId(Long eventId) {
        //var tickets = ticketRepo.findByEventId(eventId);
        var tickets = ticketRepo.findAvailableByEventId(eventId);
        var ticketDtos = tickets.stream()
                .map(ticketMapper::toDto)
                .toList();

        return ticketDtos;
    }
}
