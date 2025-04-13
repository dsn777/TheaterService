package com.dsn.Theater.API.mapper;

import com.dsn.Theater.API.dto.in.PurchaseRequestBySeatsAndEvent;
import com.dsn.Theater.API.dto.in.PurchaseRequestByTickets;
import com.dsn.Theater.API.dto.out.PurchaseDto;
import com.dsn.Theater.API.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TicketMapper.class, EventMapper.class, SeatMapper.class})
public interface PurchaseMapper {
    @Mapping(target = "shortTicketDtos", source = "tickets")
    PurchaseDto toDto(Purchase purchase);

    @Mapping(target = "firstName", source = "contact.firstName")
    @Mapping(target = "lastName", source = "contact.lastName")
    @Mapping(target = "email", source = "contact.email")
    @Mapping(target = "phone", source = "contact.phone")
    Purchase toEntity(PurchaseRequestBySeatsAndEvent purchaseRequestDto);

    @Mapping(target = "firstName", source = "contact.firstName")
    @Mapping(target = "lastName", source = "contact.lastName")
    @Mapping(target = "email", source = "contact.email")
    @Mapping(target = "phone", source = "contact.phone")
    @Mapping(target = "tickets", ignore = true)
    Purchase toEntity(PurchaseRequestByTickets purchaseRequestDto);
}
