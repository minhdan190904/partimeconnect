package com.epu.partimeconnect.service;

import com.epu.partimeconnect.dto.JobRequest;
import com.epu.partimeconnect.entity.Employer;
import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.exception.BadRequestException;
import com.epu.partimeconnect.exception.ResourceNotFoundException;
import com.epu.partimeconnect.repository.EmployerRepository;
import com.epu.partimeconnect.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final FileStorageService fileStorageService;

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public List<Job> getPublicJobs(String jobGroup) {
        if (jobGroup != null && !jobGroup.isBlank()) {
            return jobRepository.findByPublishedTrueAndStatusAndJobGroupOrderByCreatedAtDesc("OPEN", jobGroup);
        }
        return jobRepository.findByPublishedTrueAndStatusOrderByCreatedAtDesc("OPEN");
    }

    public Job getById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    }

    public Job getBySlug(String slug) {
        return jobRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    }

    public Job create(JobRequest request, MultipartFile image) {
        Job job = new Job();
        mapFields(job, request);
        job.setImageUrl(fileStorageService.store(image, "jobs"));
        return jobRepository.save(job);
    }

    public Job update(Long id, JobRequest request, MultipartFile image) {
        Job job = getById(id);
        mapFields(job, request);
        if (image != null && !image.isEmpty()) {
            fileStorageService.deleteByUrl(job.getImageUrl());
            job.setImageUrl(fileStorageService.store(image, "jobs"));
        }
        return jobRepository.save(job);
    }

    public void delete(Long id) {
        Job job = getById(id);
        fileStorageService.deleteByUrl(job.getImageUrl());
        jobRepository.delete(job);
    }

    private void mapFields(Job job, JobRequest request) {
        if (request.getEmployerId() == null) {
            throw new BadRequestException("Employer is required");
        }
        Employer employer = employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));
        job.setEmployer(employer);
        job.setTitle(request.getTitle());
        job.setSlug(buildSlug(request.getSlug(), request.getTitle()));
        job.setShortDescription(request.getShortDescription());
        job.setDescription(request.getDescription());
        job.setRequirements(request.getRequirements());
        job.setBenefits(request.getBenefits());
        job.setJobGroup(request.getJobGroup());
        job.setSalaryAmount(request.getSalaryAmount());
        job.setSalaryUnit(request.getSalaryUnit());
        job.setWorkingType(request.getWorkingType());
        job.setShiftLabel(request.getShiftLabel());
        job.setWorkingTimeText(request.getWorkingTimeText());
        job.setQuantity(request.getQuantity());
        job.setProvince(request.getProvince());
        job.setDistrict(request.getDistrict());
        job.setWorkAddress(request.getWorkAddress());
        job.setLatitude(request.getLatitude());
        job.setLongitude(request.getLongitude());
        job.setStatus(request.getStatus() == null || request.getStatus().isBlank() ? "OPEN" : request.getStatus());
        job.setPublished(request.getPublished() == null ? Boolean.TRUE : request.getPublished());
    }

    private String buildSlug(String slug, String title) {
        String value = (slug != null && !slug.isBlank()) ? slug : title;
        if (value == null || value.isBlank()) {
            throw new BadRequestException("Title is required");
        }
        return value.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-");
    }
}
