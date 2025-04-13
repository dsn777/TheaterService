package com.dsn.Theater.API.service.interfaces;

import com.dsn.Theater.API.dto.out.PerformanceDto;

import java.util.List;

public interface PerformanceService {
    List<PerformanceDto> getPerformances();
}
