package com.dsn.Theater.API.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TicketDto {
    private Long id;
    //избыточен поскольку просмотр по id
    @JsonProperty("event")
    private EventDto eventDto;

    @JsonProperty("seat")
    private SeatDto seatDto;
    private Integer priceGroup;
    private BigDecimal price;
}
