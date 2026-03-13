package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.EmployerRequest;
import com.epu.partimeconnect.entity.Employer;
import com.epu.partimeconnect.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/employers")
public class AdminEmployerPageController {

    private final EmployerService employerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employers", employerService.getAll());
        return "admin/employers/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employer", new Employer());
        return "admin/employers/form";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute EmployerRequest request,
                        @RequestParam(value = "image", required = false) MultipartFile image) {
        System.out.println("=== HIT /admin/jobs/store ===");
        employerService.create(request, image);
        return "redirect:/admin/employers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employer", employerService.getById(id));
        return "admin/employers/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute EmployerRequest request,
                         @RequestParam(value = "image", required = false) MultipartFile image) {
        employerService.update(id, request, image);
        return "redirect:/admin/employers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employerService.delete(id);
        return "redirect:/admin/employers";
    }
}