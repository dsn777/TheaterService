package com.dsn.Theater.API.controller;

import com.dsn.Theater.API.cache.TicketCacheService;
import com.dsn.Theater.API.entity.Event;
import com.dsn.Theater.API.entity.Performance;
import com.dsn.Theater.API.entity.Seat;
import com.dsn.Theater.API.entity.Ticket;
import com.dsn.Theater.API.repository.EventRepo;
import com.dsn.Theater.API.repository.PerformanceRepo;
import com.dsn.Theater.API.repository.SeatRepo;
import com.dsn.Theater.API.repository.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InitController {
    private final TicketCacheService ticketCacheService;
    private final TicketRepo ticketRepo;
    private final EventRepo eventRepo;
    private final SeatRepo seatRepo;
    private final PerformanceRepo performanceRepo;

    @GetMapping("/init")
    public void addTicket() {
        BigDecimal price = new BigDecimal(3000);
        Long seat_id = 1L;
        Integer price_group = 1;
        Long event_id = 1L;
        Seat seat = seatRepo.findById(seat_id).orElseThrow();
        Performance performance = performanceRepo.findById(1L).orElseThrow();

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            Event event = new Event();
            event.setDate(LocalDateTime.now().plusDays(15));
            event.setDate(LocalDateTime.now().plusDays(15));
            event.setPerformance(performance);
            events.add(event);
        }

        var savedEvents = (List<Event>) eventRepo.saveAll(events);
        List<Ticket> tickets = new ArrayList<>();
        for (Event savedEvent : savedEvents) {
            for (int j = 0; j < 1500; j++) {
                Ticket ticket = new Ticket();
                ticket.setEvent(savedEvent);
                ticket.setSeat(seat);
                ticket.setPrice(price);
                ticket.setPriceGroup(price_group);
                tickets.add(ticket);
            }
        }

        ticketRepo.saveAll(tickets);
    }
}
