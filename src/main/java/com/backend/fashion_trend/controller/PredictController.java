package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.PredictRequest;
import com.backend.fashion_trend.dtos.TrendResponse;
import com.backend.fashion_trend.services.PredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/model")
public class PredictController {
    private final PredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<Mono<TrendResponse>> predict(
            @RequestBody @Valid PredictRequest request
    ) {
        return ResponseEntity.ok(predictionService.predict(request));
    }

    @GetMapping("/trends")
    public ResponseEntity<Mono<TrendResponse>> getAllTrends() {
        return ResponseEntity.ok(predictionService.getAllSegments());
    }
}
