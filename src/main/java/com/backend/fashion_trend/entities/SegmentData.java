package com.backend.fashion_trend.entities;

import com.backend.fashion_trend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "segments")
public class SegmentData {

    @Id
    private String id;

    private String txt_content;

    @Indexed(direction = IndexDirection.ASCENDING)
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
