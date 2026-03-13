package com.epu.partimeconnect.repository;

import com.epu.partimeconnect.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobIdOrderByCreatedAtDesc(Long jobId);
    long countByStatus(String status);
}
