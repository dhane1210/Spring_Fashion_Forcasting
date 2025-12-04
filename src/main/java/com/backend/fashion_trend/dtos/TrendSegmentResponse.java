package com.backend.fashion_trend.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class TrendSegmentResponse {
    // These fields MUST match the keys in your Flask JSON output
    private int id;
    private String title;
    private String subtitle;
    private String buying_focus;

    // "stats" is a nested object in JSON, so we use a Map in Java
    // Example: "stats": { "emergence_score": 8.8, ... }
    private Map<String, Object> stats;
}
