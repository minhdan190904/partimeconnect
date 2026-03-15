package com.epu.partimeconnect.repository;

import com.epu.partimeconnect.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPublishedTrueAndStatusOrderByCreatedAtDesc(String status);
    List<Job> findByPublishedTrueAndStatusAndJobGroupOrderByCreatedAtDesc(String status, String jobGroup);
    List<Job> findByEmployerIdOrderByCreatedAtDesc(Long employerId);
    Optional<Job> findBySlug(String slug);

    @Query("SELECT j FROM Job j WHERE j.published = true AND j.status = 'OPEN' AND (" +
            "LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.workAddress) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.province) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            ") ORDER BY j.createdAt DESC")
    List<Job> searchPublicJobs(@Param("keyword") String keyword);

    @Query("SELECT j FROM Job j WHERE j.published = true AND j.status = 'OPEN' AND (" +
            "(LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.workAddress) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.province) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:jobGroup IS NULL OR j.jobGroup = :jobGroup) " +
            ") ORDER BY j.createdAt DESC")
    List<Job> searchPublicJobsWithGroup(@Param("keyword") String keyword, @Param("jobGroup") String jobGroup);

    @Query("SELECT j FROM Job j WHERE j.published = true AND j.status = 'OPEN' AND LOWER(j.workAddress) LIKE LOWER(CONCAT('%', :area, '%')) ORDER BY j.createdAt DESC")
    List<Job> searchByArea(@Param("area") String area);

    @Query("SELECT j FROM Job j WHERE j.published = true AND j.status = 'OPEN' AND (LOWER(j.workAddress) LIKE LOWER(CONCAT('%', :area, '%')) OR LOWER(j.province) LIKE LOWER(CONCAT('%', :area, '%'))) ORDER BY j.createdAt DESC")
    List<Job> searchByAreaOrProvince(@Param("area") String area);
}
