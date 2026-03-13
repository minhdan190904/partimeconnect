package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.dto.JobApplicationRequest;
import com.epu.partimeconnect.entity.JobApplication;
import com.epu.partimeconnect.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public/applications")
@RequiredArgsConstructor
public class PublicApplicationController {
    private final JobApplicationService jobApplicationService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ApiResponse<JobApplication> create(@ModelAttribute JobApplicationRequest request,
                                              @RequestPart(value = "cvFile", required = false) MultipartFile cvFile) {
        return new ApiResponse<>(true, "Applied", jobApplicationService.create(request, cvFile));
    }
}
