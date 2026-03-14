package com.epu.partimeconnect.service;

import com.epu.partimeconnect.dto.EmployerRequest;
import com.epu.partimeconnect.entity.Employer;
import com.epu.partimeconnect.exception.ResourceNotFoundException;
import com.epu.partimeconnect.repository.EmployerRepository;
import com.epu.partimeconnect.repository.JobApplicationRepository;
import com.epu.partimeconnect.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final FileStorageService fileStorageService;
    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public List<Employer> getAll() {
        return employerRepository.findAll();
    }

    public Employer getById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));
    }

    public Employer create(EmployerRequest request, MultipartFile image) {
        Employer employer = new Employer();
        mapFields(employer, request);
        employer.setImageUrl(fileStorageService.store(image, "employers"));
        return employerRepository.save(employer);
    }

    public Employer update(Long id, EmployerRequest request, MultipartFile image) {
        Employer employer = getById(id);
        mapFields(employer, request);
        if (image != null && !image.isEmpty()) {
            fileStorageService.deleteByUrl(employer.getImageUrl());
            employer.setImageUrl(fileStorageService.store(image, "employers"));
        }
        return employerRepository.save(employer);
    }

    public void delete(Long id) {
        Employer employer = getById(id);
        // Xóa tất cả job liên quan trước khi xóa employer
        List<com.epu.partimeconnect.entity.Job> jobs = jobRepository.findByEmployerIdOrderByCreatedAtDesc(id);
        for (com.epu.partimeconnect.entity.Job job : jobs) {
            // Xóa tất cả job_application liên quan trước khi xóa job
            List<com.epu.partimeconnect.entity.JobApplication> apps = jobApplicationRepository.findByJobIdOrderByCreatedAtDesc(job.getId());
            for (com.epu.partimeconnect.entity.JobApplication app : apps) {
                jobApplicationRepository.delete(app);
            }
            jobRepository.delete(job);
        }
        fileStorageService.deleteByUrl(employer.getImageUrl());
        employerRepository.delete(employer);
    }

    private void mapFields(Employer employer, EmployerRequest request) {
        employer.setName(request.getName());
        employer.setContactPerson(request.getContactPerson());
        employer.setPhone(request.getPhone());
        employer.setEmail(request.getEmail());
        employer.setAddress(request.getAddress());
        employer.setDescription(request.getDescription());
        employer.setStatus(request.getStatus() == null || request.getStatus().isBlank() ? "ACTIVE" : request.getStatus());
    }
}
