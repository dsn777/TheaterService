package com.dsn.Theater.API.dto.out.shortDto;

import com.dsn.Theater.API.entity.enums.PerformanceType;
import lombok.Data;

@Data
public class ShortPerformanceDto {
    private String name;
    private String duration;
    private PerformanceType type;
}
