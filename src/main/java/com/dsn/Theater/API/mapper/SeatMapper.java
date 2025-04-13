package com.dsn.Theater.API.mapper;

import com.dsn.Theater.API.dto.out.SeatDto;
import com.dsn.Theater.API.dto.out.shortDto.ShortSeatDto;
import com.dsn.Theater.API.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatDto toDto(Seat seat);
    ShortSeatDto toShortDto(Seat seat);
}
