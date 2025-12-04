package com.backend.fashion_trend.services;

import com.backend.fashion_trend.dtos.TrendSegmentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrendService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<TrendSegmentResponse> getTrendsFromAI() {
        try {
            System.out.println("--- JAVA: Calling Flask AI Model... ---");

            // 1. Make the HTTP GET request to Python
            // The URL of your running Flask "Brain"
            String FLASK_API_URL = "http://localhost:5001/get_all_trends";
            TrendSegmentResponse[] response = restTemplate.getForObject(FLASK_API_URL, TrendSegmentResponse[].class);

            System.out.println("--- JAVA: Received " + response.length + " segments from AI ---");

            // 2. Convert array to List and return
            return Arrays.asList(response);

        } catch (Exception e) {
            // If Python is down, print error and return empty list (or throw exception)
            System.err.println("!!! ERROR: Could not connect to Flask. Is python app.py running? !!!");
            e.printStackTrace();
            throw new RuntimeException("Backend AI Service Unreachable");
        }
    }
}
