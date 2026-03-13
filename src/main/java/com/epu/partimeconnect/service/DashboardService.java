package com.epu.partimeconnect.service;

import com.epu.partimeconnect.repository.EmployerRepository;
import com.epu.partimeconnect.repository.JobApplicationRepository;
import com.epu.partimeconnect.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final EmployerRepository employerRepository;
    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public Map<String, Object> getOverview() {
        return Map.of(
                "totalEmployers", employerRepository.count(),
                "totalJobs", jobRepository.count(),
                "totalApplications", jobApplicationRepository.count(),
                "newApplications", jobApplicationRepository.countByStatus("NEW")
        );
    }
}
