package com.backend.fashion_trend.dtos;

import com.backend.fashion_trend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionRequest {
    private String text;
    private int age;
    private Gender gender;
    private String region;
}
