package com.dsn.Theater.API.controller;

import com.dsn.Theater.API.dto.out.PerformanceDto;
import com.dsn.Theater.API.service.interfaces.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping
    public ResponseEntity<?> getAllPerformances() {
        List<PerformanceDto> performanceDtos = performanceService.getPerformances();
        return ResponseEntity.ok(performanceDtos);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getPerformanceById(@PathVariable Long id) {
        //допилить функционал
        List<PerformanceDto> performanceDtos = performanceService.getPerformances();
        return ResponseEntity.ok(performanceDtos);
    }
}
