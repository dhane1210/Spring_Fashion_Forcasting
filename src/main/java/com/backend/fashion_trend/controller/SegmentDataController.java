package com.backend.fashion_trend.controller;

import com.backend.fashion_trend.dtos.MonthCount;
import com.backend.fashion_trend.dtos.YearCount;
import com.backend.fashion_trend.services.SegmentDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/segments")
public class SegmentDataController {

    private final SegmentDataService segmentDataService;

    @GetMapping("/charts/monthly")
    public List<MonthCount> getMonthlyChart(
            @RequestParam int segmentId,
            @RequestParam int topicId
    ) {
        log.info("Monthly chart request: segmentId={}, topicId={}", segmentId, topicId);
        return segmentDataService.getMonthlyChart(segmentId, topicId);
    }

    @GetMapping("/charts/yearly")
    public List<YearCount> getYearlyChart(
            @RequestParam int segmentId,
            @RequestParam int topicId
    ) {
        log.info("Yearly chart request: segmentId={}, topicId={}", segmentId, topicId);
        return segmentDataService.getYearlyChart(segmentId, topicId);
    }
}
