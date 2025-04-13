package com.dsn.Theater.API.cache;

import com.dsn.Theater.API.dto.out.TicketDto;

import java.util.List;

public interface TicketCacheService {
    String CACHE_NAME = "TICKET_DTOS";
    List<TicketDto> remove(Long eventId, List<Long> ids);
    void remove(Long eventId, Long id);
    void clear();
}
