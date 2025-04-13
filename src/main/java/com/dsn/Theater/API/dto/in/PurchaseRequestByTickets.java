package com.dsn.Theater.API.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PurchaseRequestByTickets {
    @Valid
    private Contact contact;
    @JsonProperty("tickets")
    private List<Long> ticketIds;
}
