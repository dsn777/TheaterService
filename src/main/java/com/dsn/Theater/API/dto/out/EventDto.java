package com.dsn.Theater.API.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private LocalDateTime date;

    @JsonProperty(value = "performance")
    private PerformanceDto performanceDto;
}
