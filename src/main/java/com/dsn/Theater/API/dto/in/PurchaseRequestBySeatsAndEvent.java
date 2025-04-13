package com.dsn.Theater.API.dto.in;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PurchaseRequestBySeatsAndEvent {
    @Valid
    private Contact contact;
    private List<Long> seats;
    private Long eventId;
}
