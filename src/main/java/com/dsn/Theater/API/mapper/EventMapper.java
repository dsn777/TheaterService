package com.dsn.Theater.API.mapper;

import com.dsn.Theater.API.dto.out.EventDto;
import com.dsn.Theater.API.dto.out.shortDto.ShortEventDto;
import com.dsn.Theater.API.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PerformanceMapper.class})
public interface EventMapper {

    @Mapping(target = "performanceDto", source = "performance")
    EventDto toDto(Event event);

    @Mapping(target = "shortPerformanceDto", source = "performance")
    ShortEventDto toShortDto(Event event);
}
