package com.backend.fashion_trend.services;

import com.backend.fashion_trend.dtos.MonthCount;
import com.backend.fashion_trend.dtos.SegmentRequest;
import com.backend.fashion_trend.dtos.SegmentResponse;
import com.backend.fashion_trend.dtos.YearCount;
import com.backend.fashion_trend.mappers.SegmentMapper;
import com.backend.fashion_trend.repositories.SegmentDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SegmentDataService {
    private final SegmentDataRepository segmentDataRepository;
    private final SegmentMapper segmentMapper;

    public List<MonthCount> getMonthlyChart(int segmentId, int topicId) {
        return segmentDataRepository.getMonthlyCounts(segmentId, topicId);
    }

    public List<YearCount> getYearlyChart(int segmentId, int topicId) {
        return segmentDataRepository.getYearlyCounts(segmentId, topicId);
    }

    public List<SegmentResponse> addSegmentData(List<SegmentRequest> requests) {
        var segments = segmentMapper.toSegmentList(requests);
        return segmentMapper.toSegmentResponseList(segmentDataRepository.saveAll(segments));
    }
}
