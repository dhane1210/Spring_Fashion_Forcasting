package com.backend.fashion_trend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrendSegmentResponse {
    private int id;
    private String title;
    private String subtitle;

    // Maps "buying_focus" (Python) -> "buyingFocus" (Java)
    @JsonProperty("buying_focus")
    private String buyingFocus;

    // Nested Stats Object
    private TrendStats stats;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrendStats {
        @JsonProperty("member_count")
        private int memberCount;

        @JsonProperty("emergence_score")
        private double emergenceScore;

        private String status;

        // --- NEW FIELDS FROM AI MODEL ---
        @JsonProperty("sentiment_score")
        private double sentimentScore;

        @JsonProperty("sentiment_label")
        private String sentimentLabel;

        @JsonProperty("predicted_growth")
        private String predictedGrowth;
    }
}