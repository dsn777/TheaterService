package com.dsn.Theater.API.service.impl;

import com.dsn.Theater.API.dto.out.PerformanceDto;
import com.dsn.Theater.API.entity.Performance;
import com.dsn.Theater.API.mapper.PerformanceMapper;
import com.dsn.Theater.API.repository.PerformanceRepo;
import com.dsn.Theater.API.service.interfaces.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepo performanceRepo;
    private final PerformanceMapper performanceMapper;

    @Override
    public List<PerformanceDto> getPerformances() {
        List<Performance> performances = performanceRepo.findAll();
        return performances.stream()
                .map(performanceMapper::toDto)
                .toList();
    }
}
