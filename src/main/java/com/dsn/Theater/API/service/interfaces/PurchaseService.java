package com.dsn.Theater.API.service.interfaces;

import com.dsn.Theater.API.dto.in.PurchaseRequestBySeatsAndEvent;
import com.dsn.Theater.API.dto.in.PurchaseRequestByTickets;
import com.dsn.Theater.API.dto.out.PurchaseDto;

import java.util.concurrent.CompletableFuture;

public interface PurchaseService {
    PurchaseDto buyTicketBySeatsAndEvent(PurchaseRequestBySeatsAndEvent purchaseRequestDto);
    CompletableFuture<PurchaseDto> buyTicketByTicketIds(PurchaseRequestByTickets request);
}
