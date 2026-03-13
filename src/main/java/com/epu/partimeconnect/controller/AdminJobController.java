package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.dto.JobRequest;
import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/jobs")
@RequiredArgsConstructor
public class AdminJobController {
    private final JobService jobService;

    @GetMapping
    public ApiResponse<List<Job>> getAll() {
        return new ApiResponse<>(true, "Success", jobService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Job> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, "Success", jobService.getById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ApiResponse<Job> create(@ModelAttribute JobRequest request,
                                   @RequestPart(value = "image", required = false) MultipartFile image) {
        return new ApiResponse<>(true, "Created", jobService.create(request, image));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ApiResponse<Job> update(@PathVariable Long id,
                                   @ModelAttribute JobRequest request,
                                   @RequestPart(value = "image", required = false) MultipartFile image) {
        return new ApiResponse<>(true, "Updated", jobService.update(id, request, image));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        jobService.delete(id);
        return new ApiResponse<>(true, "Deleted", null);
    }
}
