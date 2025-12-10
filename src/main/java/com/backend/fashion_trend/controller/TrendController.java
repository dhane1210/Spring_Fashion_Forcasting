package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.TrendSegmentResponse;
import com.backend.fashion_trend.services.TrendService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/trends")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allows your frontend to connect
public class TrendController {

    private final TrendService trendService;

    @GetMapping("/all")
    public ResponseEntity<@NotNull List<TrendSegmentResponse>> getAllTrends() {
        // 1. Fetch from AI Service
        List<TrendSegmentResponse> trends = trendService.getTrendsFromAI();

        // 2. Return JSON to Frontend
        return ResponseEntity.ok(trends);
    }
}