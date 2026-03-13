package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.JobApplicationRequest;
import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.service.EmployerService;
import com.epu.partimeconnect.service.JobApplicationService;
import com.epu.partimeconnect.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final JobService jobService;
    private final EmployerService employerService;
    private final JobApplicationService jobApplicationService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("jobsTheoGio", jobService.getPublicJobs("THEO_GIO"));
        model.addAttribute("jobsTheoCa", jobService.getPublicJobs("THEO_CA"));
        model.addAttribute("jobsCoDinh", jobService.getPublicJobs("CO_DINH"));
        model.addAttribute("totalJobs", jobService.getAll().size());
        model.addAttribute("totalEmployers", employerService.getAll().size());
        return "public/home";
    }

    @GetMapping("/jobs")
    public String jobsPage(@RequestParam(required = false) String jobGroup, Model model) {
        model.addAttribute("jobs", jobService.getPublicJobs(jobGroup));
        return "public/jobs";
    }

    @GetMapping("/jobs/{id}")
    public String jobDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getById(id));
        return "public/job-detail";
    }

    @GetMapping("/jobs/slug/{slug}")
    public String jobDetailBySlugPage(@PathVariable String slug, Model model) {
        model.addAttribute("job", jobService.getBySlug(slug));
        return "public/job-detail";
    }

    @GetMapping("/jobs/{id}/apply")
    public String applyPage(@PathVariable Long id, Model model) {
        Job job = jobService.getById(id);
        model.addAttribute("job", job);
        return "public/apply";
    }

    @PostMapping("/jobs/{id}/apply")
    public String submitApply(@PathVariable Long id,
                              @ModelAttribute JobApplicationRequest request,
                              @RequestParam(value = "cvFile", required = false) MultipartFile cvFile) {
        request.setJobId(id);
        jobApplicationService.create(request, cvFile);
        return "redirect:/apply-success";
    }

    @GetMapping("/apply-success")
    public String applySuccessPage() {
        return "public/apply-success";
    }

    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin/auth/login";
    }
}