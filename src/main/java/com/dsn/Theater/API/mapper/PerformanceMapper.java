package com.dsn.Theater.API.mapper;

import com.dsn.Theater.API.dto.out.PerformanceDto;
import com.dsn.Theater.API.dto.out.shortDto.ShortPerformanceDto;
import com.dsn.Theater.API.entity.Performance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PerformanceMapper {
    PerformanceDto toDto(Performance performance);
    ShortPerformanceDto toShortDto(Performance performance);
}
