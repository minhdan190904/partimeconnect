package com.epu.partimeconnect.repository;

import com.epu.partimeconnect.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPublishedTrueAndStatusOrderByCreatedAtDesc(String status);
    List<Job> findByPublishedTrueAndStatusAndJobGroupOrderByCreatedAtDesc(String status, String jobGroup);
    List<Job> findByEmployerIdOrderByCreatedAtDesc(Long employerId);
    Optional<Job> findBySlug(String slug);
}
