package com.backend.fashion_trend.mappers;

import com.backend.fashion_trend.dtos.SegmentRequest;
import com.backend.fashion_trend.dtos.SegmentResponse;
import com.backend.fashion_trend.entities.SegmentData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SegmentMapper {
    List<SegmentData> toSegmentList(List<SegmentRequest> requests);

    List<SegmentResponse> toSegmentResponseList(List<SegmentData> segments);
}
