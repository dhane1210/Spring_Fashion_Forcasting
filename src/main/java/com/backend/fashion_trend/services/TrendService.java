package com.backend.fashion_trend.services;

import com.backend.fashion_trend.dtos.PredictionRequest;
import com.backend.fashion_trend.dtos.PredictionResponse;
import com.backend.fashion_trend.dtos.TrendSegmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrendService {

    private final String FLASK_BASE_URL = "http://localhost:5001";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<TrendSegmentResponse> getTrendsFromAI() {
        try {
            String url = FLASK_BASE_URL + "/get_all_trends";
            log.info("Fetching trends from: {}", url);
            TrendSegmentResponse[] response = restTemplate.getForObject(url, TrendSegmentResponse[].class);
            if (response != null) {
                return Arrays.asList(response);
            }
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Flask API Error: {}", e.getMessage());
            throw new RuntimeException("AI Service Unreachable");
        }
    }

    public PredictionResponse predictUserSegment(PredictionRequest request) {
        try {
            String url = FLASK_BASE_URL + "/predict";
            log.info("Sending prediction request for: {}", request.getText());
            return restTemplate.postForObject(url, request, PredictionResponse.class);
        } catch (Exception e) {
            log.error("Flask Prediction Error: {}", e.getMessage());
            throw new RuntimeException("AI Prediction Failed");
        }
    }
}