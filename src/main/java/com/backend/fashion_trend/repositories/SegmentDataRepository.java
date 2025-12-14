package com.backend.fashion_trend.repositories;

import com.backend.fashion_trend.entities.SegmentData;
import com.backend.fashion_trend.dtos.MonthCount;
import com.backend.fashion_trend.dtos.YearCount;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SegmentDataRepository extends MongoRepository<@NotNull SegmentData, @NotNull String> {
    @Aggregation(pipeline = {
            "{ $match: { segment_id: ?0, topic_id: ?1 } }",
            "{ $project: { month: { $dateToString: { format: '%Y-%m', date: '$timestamp' } } } }",
            "{ $group: { _id: '$month', count: { $sum: 1 } } }",
            "{ $project: { month: '$_id', count: 1, _id: 0 } }",
            "{ $sort: { month: 1 } }"
    })
    List<MonthCount> getMonthlyCounts(int segmentId, int topicId);

    @Aggregation(pipeline = {
            "{ $match: { segment_id: ?0, topic_id: ?1 } }",
            "{ $project: { year: { $dateToString: { format: '%Y', date: '$timestamp' } } } }",
            "{ $group: { _id: '$year', count: { $sum: 1 } } }",
            "{ $project: { year: '$_id', count: 1, _id: 0 } }",
            "{ $sort: { year: 1 } }"
    })
    List<YearCount> getYearlyCounts(int segmentId, int topicId);
    List<SegmentData> findAllByOrderByTimestampDesc(Pageable pageable);
}