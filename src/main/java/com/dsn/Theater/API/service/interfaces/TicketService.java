package com.dsn.Theater.API.service.interfaces;

import com.dsn.Theater.API.dto.out.TicketDto;

import java.util.List;

public interface TicketService {
    void setTicketGroup();
    void checkAvailableTickets();
    List<TicketDto> getAvailableTicketsByEventId(Long eventId);
}
