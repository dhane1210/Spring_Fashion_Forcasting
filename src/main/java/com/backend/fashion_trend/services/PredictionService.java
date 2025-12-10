package com.backend.fashion_trend.services;

import com.backend.fashion_trend.dtos.PredictRequest;
import com.backend.fashion_trend.dtos.TrendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PredictionService {
    private final WebClient webClient;

    public Mono<TrendResponse> predict(PredictRequest request) {
        return webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TrendResponse.class);
    }

    public Mono<TrendResponse> getAllSegments() {
        return webClient.get()
                .uri("/get_all_trends")
                .retrieve()
                .bodyToMono(TrendResponse.class);
    }
}
