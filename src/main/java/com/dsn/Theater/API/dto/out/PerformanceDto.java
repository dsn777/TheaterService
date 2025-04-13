package com.dsn.Theater.API.dto.out;

import com.dsn.Theater.API.entity.enums.PerformanceType;
import lombok.Data;

@Data
public class PerformanceDto {
    private String name;
    private String duration;
    private PerformanceType type;
    private String description;
}
