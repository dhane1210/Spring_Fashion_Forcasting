package com.backend.fashion_trend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient predictionServer() {
        return WebClient.builder()
                .baseUrl("http://localhost:5001/")
                .build();
    }
}
