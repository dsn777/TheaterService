package com.dsn.Theater.API.controller;

import com.dsn.Theater.API.dto.in.PurchaseRequestBySeatsAndEvent;
import com.dsn.Theater.API.dto.in.PurchaseRequestByTickets;
import com.dsn.Theater.API.dto.out.PurchaseDto;
import com.dsn.Theater.API.entity.Purchase;
import com.dsn.Theater.API.service.impl.PurchaseServiceImpl;
import com.dsn.Theater.API.service.interfaces.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/bySeats")
    public ResponseEntity<PurchaseDto> makePurchaseBySeats(@Valid @RequestBody PurchaseRequestBySeatsAndEvent purchaseRequestDto) {
        var purchaseDto = purchaseService.buyTicketBySeatsAndEvent(purchaseRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(purchaseDto);
    }

    @PostMapping("/byTickets")
    public CompletableFuture<ResponseEntity<?>> makePurchaseByTickets(@Valid @RequestBody PurchaseRequestByTickets purchaseRequestDto) {
        var purchaseDtoCf = purchaseService.buyTicketByTicketIds(purchaseRequestDto);
        return purchaseDtoCf.thenApply(dto -> ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto)
        );
    }

    @GetMapping("/{id}")
    public Purchase getById(@PathVariable UUID id) {
        return ((PurchaseServiceImpl) purchaseService).getById(id);
    }
}
