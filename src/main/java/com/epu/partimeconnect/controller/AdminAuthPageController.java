package com.epu.partimeconnect.controller;

import com.epu.partimeconnect.dto.AdminLoginRequest;
import com.epu.partimeconnect.exception.BadRequestException;
import com.epu.partimeconnect.service.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAuthPageController {

    private final AdminService adminService;

    @PostMapping("/do-login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        try {
            AdminLoginRequest request = new AdminLoginRequest();
            request.setUsername(username);
            request.setPassword(password);

            Map<String, Object> admin = adminService.login(request);

            session.setAttribute("adminId", admin.get("id"));
            session.setAttribute("adminName", admin.get("fullName"));
            session.setAttribute("adminUsername", admin.get("username"));

            return "redirect:/admin/dashboard";
        } catch (BadRequestException ex) {
            model.addAttribute("error", ex.getMessage());
            return "admin/auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}