package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.service.DashboardService;
import com.epu.partimeconnect.service.JobApplicationService;
import com.epu.partimeconnect.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminDashboardPageController {

    private final DashboardService dashboardService;
    private final JobService jobService;
    private final JobApplicationService jobApplicationService;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        Map<String, Object> overview = dashboardService.getOverview();
        model.addAttribute("totalEmployers", overview.get("totalEmployers"));
        model.addAttribute("totalJobs", overview.get("totalJobs"));
        model.addAttribute("totalApplications", overview.get("totalApplications"));
        model.addAttribute("newApplications", overview.get("newApplications"));
        model.addAttribute("latestJobs", jobService.getAll().stream().limit(5).toList());
        model.addAttribute("latestApplications", jobApplicationService.getAll().stream().limit(6).toList());
        return "admin/dashboard";
    }
}