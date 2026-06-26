package com.zetheta.processor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zetheta.processor.dto.AnalyticsResponse;
import com.zetheta.processor.service.AnalyticsService;

@RestController
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(
            AnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @GetMapping("/api/analytics")
    public AnalyticsResponse analytics() {

        return analyticsService.getAnalytics();
    }
}