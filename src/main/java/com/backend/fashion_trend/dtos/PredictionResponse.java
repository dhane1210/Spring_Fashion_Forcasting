package com.backend.fashion_trend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResponse {

    @JsonProperty("predicted_topic")
    private String predictedTopic;

    @JsonProperty("assigned_segment")
    private int assignedSegment;

    private String message;
}
