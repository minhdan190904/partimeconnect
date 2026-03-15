package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/jobs")
@RequiredArgsConstructor
public class PublicJobController {
    private final JobService jobService;

    @GetMapping
    public ApiResponse<List<Job>> getPublicJobs(@RequestParam(required = false) String jobGroup,
                                                @RequestParam(required = false) String keyword,
                                                @RequestParam(required = false) String area) {
        return new ApiResponse<>(true, "Success", jobService.getPublicJobs(jobGroup, keyword, area));
    }

    @GetMapping("/{id}")
    public ApiResponse<Job> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, "Success", jobService.getById(id));
    }

    @GetMapping("/slug/{slug}")
    public ApiResponse<Job> getBySlug(@PathVariable String slug) {
        return new ApiResponse<>(true, "Success", jobService.getBySlug(slug));
    }
}
