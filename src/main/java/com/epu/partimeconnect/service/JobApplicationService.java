package com.epu.partimeconnect.service;

import com.epu.partimeconnect.dto.JobApplicationRequest;
import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.entity.JobApplication;
import com.epu.partimeconnect.exception.ResourceNotFoundException;
import com.epu.partimeconnect.repository.JobApplicationRepository;
import com.epu.partimeconnect.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final FileStorageService fileStorageService;

    public List<JobApplication> getAll() {
        return jobApplicationRepository.findAll();
    }

    public List<JobApplication> getByJobId(Long jobId) {
        return jobApplicationRepository.findByJobIdOrderByCreatedAtDesc(jobId);
    }

    public JobApplication getById(Long id) {
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
    }

    public JobApplication create(JobApplicationRequest request, MultipartFile cvFile) {
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setFullName(request.getFullName());
        application.setPhone(request.getPhone());
        application.setEmail(request.getEmail());
        application.setBirthYear(request.getBirthYear());
        application.setGender(request.getGender());
        application.setSchoolName(request.getSchoolName());
        application.setMajor(request.getMajor());
        application.setExperienceText(request.getExperienceText());
        application.setAvailableTime(request.getAvailableTime());
        application.setNote(request.getNote());
        application.setCvFileUrl(fileStorageService.store(cvFile, "cvs"));
        application.setStatus("NEW");
        return jobApplicationRepository.save(application);
    }

    public JobApplication updateStatus(Long id, String status) {
        JobApplication application = getById(id);
        application.setStatus(status);
        return jobApplicationRepository.save(application);
    }

    public void delete(Long id) {
        JobApplication application = getById(id);
        fileStorageService.deleteByUrl(application.getCvFileUrl());
        jobApplicationRepository.delete(application);
    }
}
