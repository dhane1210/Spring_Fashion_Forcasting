package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.PredictionRequest;
import com.backend.fashion_trend.dtos.PredictionResponse;
import com.backend.fashion_trend.dtos.TrendSegmentResponse;
import com.backend.fashion_trend.services.TrendService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@RestController
@RequestMapping("/api/trends")
@RequiredArgsConstructor
public class TrendController {

    private final TrendService trendService;

    @GetMapping("/all")
    public ResponseEntity<@NotNull List<TrendSegmentResponse>> getAllTrends() {
        List<TrendSegmentResponse> trends = trendService.getTrendsFromAI();
        return ResponseEntity.ok(trends);
    }

    @PostMapping("/predict")
    public ResponseEntity<PredictionResponse> predictSegment(@RequestBody PredictionRequest request) {
        PredictionResponse response = trendService.predictUserSegment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/export")
    public void exportTrendsToCSV(HttpServletResponse response) throws IOException {
        List<TrendSegmentResponse> trends = trendService.getTrendsFromAI();

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"trend_report.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("Segment ID,Title,Buying Focus,Members,Growth Forecast,Sentiment,Status");

        for (TrendSegmentResponse t : trends) {
            writer.printf("%d,\"%s\",\"%s\",%d,\"%s\",\"%s\",\"%s\"%n",
                    t.getId(),
                    t.getTitle(),
                    t.getBuyingFocus(),
                    t.getStats().getMemberCount(),
                    t.getStats().getPredictedGrowth(),
                    t.getStats().getSentimentLabel(),
                    t.getStats().getStatus()
            );
        }
    }
}