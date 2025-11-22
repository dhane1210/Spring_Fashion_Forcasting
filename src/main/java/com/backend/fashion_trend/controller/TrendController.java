package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dto.TrendSegmentDTO;
import com.backend.fashion_trend.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/trends")
// ALLOW React (Port 5173) to talk to this Java Server
@CrossOrigin(origins = "*")
public class TrendController {

    @Autowired
    private TrendService trendService;

    @GetMapping("/all")
    public ResponseEntity<List<TrendSegmentDTO>> getAllTrends() {
        // 1. Call the Service
        List<TrendSegmentDTO> trends = trendService.getTrendsFromAI();

        // 2. Send Data back to React with HTTP 200 OK
        return ResponseEntity.ok(trends);
    }
}