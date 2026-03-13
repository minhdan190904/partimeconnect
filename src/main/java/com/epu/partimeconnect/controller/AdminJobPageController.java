package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.JobRequest;
import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.service.EmployerService;
import com.epu.partimeconnect.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/jobs")
public class AdminJobPageController {

    private final JobService jobService;
    private final EmployerService employerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("jobs", jobService.getAll());
        return "admin/jobs/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        Job job = new Job();
        job.setPublished(true);
        job.setStatus("OPEN");
        job.setJobGroup("THEO_GIO");
        job.setSalaryUnit("GIO");
        job.setWorkingType("PART_TIME");
        model.addAttribute("job", job);
        model.addAttribute("employers", employerService.getAll());
        return "admin/jobs/form";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute JobRequest request,
                        @RequestParam(value = "image", required = false) MultipartFile image) {
        jobService.create(request, image);
        return "redirect:/admin/jobs";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getById(id));
        model.addAttribute("employers", employerService.getAll());
        return "admin/jobs/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute JobRequest request,
                         @RequestParam(value = "image", required = false) MultipartFile image) {
        jobService.update(id, request, image);
        return "redirect:/admin/jobs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        jobService.delete(id);
        return "redirect:/admin/jobs";
    }
}