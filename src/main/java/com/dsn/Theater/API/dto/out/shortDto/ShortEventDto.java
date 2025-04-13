package com.dsn.Theater.API.dto.out.shortDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShortEventDto {
    private LocalDateTime date;
    @JsonProperty("performance")
    private ShortPerformanceDto shortPerformanceDto;
}
