package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/applications")
public class AdminApplicationPageController {

    private final JobApplicationService jobApplicationService;

    @GetMapping
    public String list(@RequestParam(required = false) Long jobId, Model model) {
        if (jobId != null) {
            model.addAttribute("applications", jobApplicationService.getByJobId(jobId));
        } else {
            model.addAttribute("applications", jobApplicationService.getAll());
        }
        return "admin/applications/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("application", jobApplicationService.getById(id));
        return "admin/applications/detail";
    }

    @PostMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        jobApplicationService.updateStatus(id, status);
        return "redirect:/admin/applications/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        jobApplicationService.delete(id);
        return "redirect:/admin/applications";
    }
}