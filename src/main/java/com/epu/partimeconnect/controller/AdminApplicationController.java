package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.dto.ApplicationStatusRequest;
import com.epu.partimeconnect.entity.JobApplication;
import com.epu.partimeconnect.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/applications")
@RequiredArgsConstructor
public class AdminApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping
    public ApiResponse<List<JobApplication>> getAll() {
        return new ApiResponse<>(true, "Success", jobApplicationService.getAll());
    }

    @GetMapping("/job/{jobId}")
    public ApiResponse<List<JobApplication>> getByJobId(@PathVariable Long jobId) {
        return new ApiResponse<>(true, "Success", jobApplicationService.getByJobId(jobId));
    }

    @GetMapping("/{id}")
    public ApiResponse<JobApplication> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, "Success", jobApplicationService.getById(id));
    }

    @PutMapping("/{id}/status")
    public ApiResponse<JobApplication> updateStatus(@PathVariable Long id, @RequestBody ApplicationStatusRequest request) {
        return new ApiResponse<>(true, "Updated", jobApplicationService.updateStatus(id, request.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        jobApplicationService.delete(id);
        return new ApiResponse<>(true, "Deleted", null);
    }
}
