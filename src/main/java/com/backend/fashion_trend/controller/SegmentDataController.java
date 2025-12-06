package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.MonthCount;
import com.backend.fashion_trend.dtos.SegmentRequest;
import com.backend.fashion_trend.dtos.SegmentResponse;
import com.backend.fashion_trend.dtos.YearCount;
import com.backend.fashion_trend.services.SegmentDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/segments")
public class SegmentDataController {

    private final SegmentDataService segmentDataService;

    @GetMapping("/charts/monthly")
    public ResponseEntity<@NotNull List<MonthCount>> getMonthlyChart(
            @RequestParam int segmentId,
            @RequestParam int topicId
    ) {
        log.info("Monthly chart request: segmentId={}, topicId={}", segmentId, topicId);
        return ResponseEntity.ok(segmentDataService.getMonthlyChart(segmentId, topicId));
    }

    @GetMapping("/charts/yearly")
    public ResponseEntity<@NotNull List<YearCount>> getYearlyChart(
            @RequestParam int segmentId,
            @RequestParam int topicId
    ) {
        log.info("Yearly chart request: segmentId={}, topicId={}", segmentId, topicId);
        return ResponseEntity.ok(segmentDataService.getYearlyChart(segmentId, topicId));
    }

    @PostMapping("/")
    public ResponseEntity<@NotNull List<SegmentResponse>> addSegments(
            @RequestBody @NotNull List<SegmentRequest> segmentData
    ) {
        var segmentDtoList = segmentDataService.addSegmentData(segmentData);
        return ResponseEntity.status(HttpStatus.CREATED).body(segmentDtoList);
    }

}
