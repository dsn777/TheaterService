package com.dsn.Theater.API.dto.out.shortDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShortTicketDto {
    @JsonProperty("event")
    private ShortEventDto shortEventDto;
    @JsonProperty("seat")
    private ShortSeatDto shortSeatDto;
    private BigDecimal price;
}
