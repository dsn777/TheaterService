package com.dsn.Theater.API.service.impl;

import com.dsn.Theater.API.cache.TicketCacheService;
import com.dsn.Theater.API.client.MailClient;
import com.dsn.Theater.API.dto.in.PurchaseRequestBySeatsAndEvent;
import com.dsn.Theater.API.dto.in.PurchaseRequestByTickets;
import com.dsn.Theater.API.dto.out.PurchaseDto;
import com.dsn.Theater.API.entity.Purchase;
import com.dsn.Theater.API.entity.Ticket;
import com.dsn.Theater.API.exception.TicketsNotAvailableException;
import com.dsn.Theater.API.mapper.PurchaseMapper;
import com.dsn.Theater.API.repository.PurchaseRepo;
import com.dsn.Theater.API.repository.TicketRepo;
import com.dsn.Theater.API.service.interfaces.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {
    private final TicketRepo ticketRepo;
    private final PurchaseRepo purchaseRepo;
    private final PurchaseMapper purchaseMapper;
    private final TicketCacheService ticketCacheService;
    private final MailClient mailClient;
    private final CacheManager cacheManager;

    @Cacheable(value = "purchases")
    public Purchase getById(UUID uuid) {
        return purchaseRepo.findById(uuid).orElseThrow();
    }

    @Override
    @Transactional
    public PurchaseDto buyTicketBySeatsAndEvent(PurchaseRequestBySeatsAndEvent request) {
        var tickets = ticketRepo.findAvailableBySeatsAndEvent(request.getEventId(), request.getSeats());
        if (tickets.isEmpty()) {
            throw new TicketsNotAvailableException("Tickets with these IDs are not available!");
        }

        //сохраняем покупку
        Purchase purchase = purchaseMapper.toEntity(request);
        purchase.setTickets(tickets);
        purchase.setCreatedAt(LocalDateTime.now());
        var savedPurchase = purchaseRepo.save(purchase);

        //сохраняем purchase_id
        List<Long> ticketIds = tickets.stream()
                .mapToLong(Ticket::getId)
                .boxed()
                .toList();
        int rowsUpdated = ticketRepo.updatePurchaseId(ticketIds, savedPurchase.getId());
        if (rowsUpdated != ticketIds.size()) {
            log.error("Concurrent access...");
            throw new TicketsNotAvailableException("Tickets are not avaialable! Try again");
        }

        //обновляем кэш
        ticketCacheService.remove(request.getEventId(), ticketIds);
        log.info("Purchase has been created: {}", savedPurchase.getId());

        return purchaseMapper.toDto(purchase);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<PurchaseDto> buyTicketByTicketIds(PurchaseRequestByTickets request) {
        //сохраняем покупку
        List<Ticket> tickets = ticketRepo.findAllById(request.getTicketIds());
        Purchase purchase = purchaseMapper.toEntity(request);
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setTickets(tickets);
        var savedPurchase = purchaseRepo.save(purchase);

        // обновление purchase_id в Ticket
        int rowsUpdated = ticketRepo.updatePurchaseId(request.getTicketIds(), savedPurchase.getId());
        if (rowsUpdated != request.getTicketIds().size()) {
            log.error("Attempt to buy unavailable tickets");
            throw new TicketsNotAvailableException("Tickets are not avaialable!");
        }

        //обновление кэша
        Long eventId = tickets.getFirst().getEvent().getId();
        ticketCacheService.remove(eventId, request.getTicketIds());
        log.info("Purchase has been created: {}", savedPurchase.getId());

        //отправка по email
        var savedPurchaseDto = purchaseMapper.toDto(savedPurchase);
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            mailClient.sendToMail(savedPurchaseDto);
            log.info("Successful sending by email to {}", savedPurchaseDto.getEmail());
        });

        return CompletableFuture.completedFuture(savedPurchaseDto);
    }
}
