package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.dto.EmployerRequest;
import com.epu.partimeconnect.entity.Employer;
import com.epu.partimeconnect.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/employers")
@RequiredArgsConstructor
public class AdminEmployerController {
    private final EmployerService employerService;

    @GetMapping
    public ApiResponse<List<Employer>> getAll() {
        return new ApiResponse<>(true, "Success", employerService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Employer> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, "Success", employerService.getById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ApiResponse<Employer> create(@ModelAttribute EmployerRequest request,
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
        return new ApiResponse<>(true, "Created", employerService.create(request, image));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ApiResponse<Employer> update(@PathVariable Long id,
                                        @ModelAttribute EmployerRequest request,
                                        @RequestPart(value = "image", required = false) MultipartFile image) {
        return new ApiResponse<>(true, "Updated", employerService.update(id, request, image));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        employerService.delete(id);
        return new ApiResponse<>(true, "Deleted", null);
    }
}
