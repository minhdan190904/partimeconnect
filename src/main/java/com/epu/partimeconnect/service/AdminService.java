package com.epu.partimeconnect.service;

import com.epu.partimeconnect.dto.AdminLoginRequest;
import com.epu.partimeconnect.entity.Admin;
import com.epu.partimeconnect.exception.BadRequestException;
import com.epu.partimeconnect.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService implements CommandLineRunner {
    private final AdminRepository adminRepository;

    public Map<String, Object> login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByUsernameAndStatus(request.getUsername(), "ACTIVE")
                .orElseThrow(() -> new BadRequestException("Sai tai khoan hoac mat khau"));
        if (!admin.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("Sai tai khoan hoac mat khau");
        }
        return Map.of(
                "id", admin.getId(),
                "username", admin.getUsername(),
                "fullName", admin.getFullName(),
                "email", admin.getEmail(),
                "status", admin.getStatus()
        );
    }

    @Override
    public void run(String... args) {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("123456");
            admin.setFullName("Admin Partime Connect EPU");
            admin.setEmail("admin@epu.local");
            adminRepository.save(admin);
        }
    }
}
