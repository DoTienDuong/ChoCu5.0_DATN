package com.manager.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SlideStatsResponse {
    private long totalSlides;
    private LocalDateTime latestCreatedAt;
}
