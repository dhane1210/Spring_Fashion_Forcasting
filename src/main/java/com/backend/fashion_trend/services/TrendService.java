package com.backend.fashion_trend.services;

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

    // URL of your Flask AI Microservice
    private final String FLASK_API_URL = "http://localhost:5001/get_all_trends";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<TrendSegmentResponse> getTrendsFromAI() {
        try {
            log.info("📡 Connecting to AI Brain at: {}", FLASK_API_URL);

            // Call Python/Flask API
            TrendSegmentResponse[] response = restTemplate.getForObject(FLASK_API_URL, TrendSegmentResponse[].class);

            if (response != null) {
                log.info("✅ Success! Received {} AI segments.", response.length);
                return Arrays.asList(response);
            } else {
                log.warn("⚠️ AI returned empty response.");
                return Collections.emptyList();
            }

        } catch (Exception e) {
            log.error("❌ ERROR: Could not connect to Flask AI. Is 'python app.py' running? Details: {}", e.getMessage());
            throw new RuntimeException("Backend AI Service Unreachable");
        }
    }
}