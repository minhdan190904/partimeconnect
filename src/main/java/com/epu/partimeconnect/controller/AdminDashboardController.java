package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<Map<String, Object>> getOverview() {
        return new ApiResponse<>(true, "Success", dashboardService.getOverview());
    }
}
