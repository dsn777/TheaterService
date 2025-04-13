package com.dsn.Theater.API.mapper;

import com.dsn.Theater.API.dto.out.TicketDto;
import com.dsn.Theater.API.dto.out.shortDto.ShortTicketDto;
import org.mapstruct.Mapper;
import com.dsn.Theater.API.entity.Ticket;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventMapper.class, SeatMapper.class, PerformanceMapper.class})
public interface TicketMapper {

    @Mapping(target = "eventDto", source = "event")
    @Mapping(target = "seatDto", source = "seat")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "shortEventDto", source = "event")
    @Mapping(target = "shortSeatDto", source = "seat")
    ShortTicketDto toShortDto(Ticket ticket);
}
