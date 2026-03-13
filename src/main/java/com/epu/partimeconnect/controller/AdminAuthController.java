package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.AdminLoginRequest;
import com.epu.partimeconnect.dto.ApiResponse;
import com.epu.partimeconnect.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminService adminService;

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@Valid @RequestBody AdminLoginRequest request) {
        return new ApiResponse<>(true, "Login success", adminService.login(request));
    }
}
