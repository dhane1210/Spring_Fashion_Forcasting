package com.backend.fashion_trend.dtos;

import com.backend.fashion_trend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SegmentResponse {
    private String id;

    private String txt_content;

    private LocalDateTime timestamp;

    private String category;

    private int age;

    private Gender gender;

    private String region_clean;

    private String season;

    private String clean_text;

    private int topic_id;

    private int segment_id;

    private String topic_name;
}
