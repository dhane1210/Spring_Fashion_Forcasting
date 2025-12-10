package com.backend.fashion_trend.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrendResponse {

    @Min(0)
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String buyingFocus;

    @Valid
    @NotNull
    private Stats stats;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Stats {

        @Min(0)
        private int memberCount;

        @Min(0)
        private double emergenceScore;

        @NotBlank
        private String sentimentLabel;

        @NotBlank
        private String predictedGrowth;
    }
}

