package com.dsn.Theater.API.cache.impl;

import com.dsn.Theater.API.cache.TicketCacheService;
import com.dsn.Theater.API.dto.out.TicketDto;
import com.dsn.Theater.API.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCacheServiceImpl implements TicketCacheService {
    private final TicketService ticketService;

    @Override
    @CachePut(key = "#eventId", cacheNames = TicketCacheService.CACHE_NAME)
    public List<TicketDto> remove(Long eventId, List<Long> ids) {
        var ticketDtos = ticketService.getAvailableTicketsByEventId(eventId);
        var ticketDtosCopy = new ArrayList<>(ticketDtos);
        ticketDtosCopy.removeIf(dto -> ids.contains(dto.getId()));
        return ticketDtosCopy;
    }

    @Override
    public void remove(Long eventId, Long id) {
        this.remove(eventId, List.of(id));
    }

    @Override
    @CacheEvict(key = "#eventId")
    public void clear() {
    }
}
