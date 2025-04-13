package com.dsn.Theater.API.dto.out;

import com.dsn.Theater.API.entity.enums.Level;
import com.dsn.Theater.API.entity.enums.Section;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SeatDto {
    private Long id;
    private Level level;
    private Section section;
    private Integer sectionNumber;
    private Integer number;
}
