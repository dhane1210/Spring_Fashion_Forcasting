package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.TrendSegmentResponse;
import com.backend.fashion_trend.services.TrendService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trends")
public class TrendController {

    private final TrendService trendService;

    @GetMapping("/all")
    public ResponseEntity<@NotNull List<TrendSegmentResponse>> getAllTrends() {
        List<TrendSegmentResponse> trends = trendService.getTrendsFromAI();
        return ResponseEntity.ok(trends);
    }
}