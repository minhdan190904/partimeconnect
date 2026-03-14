package com.epu.partimeconnect.service;

import com.epu.partimeconnect.entity.Job;
import com.epu.partimeconnect.entity.JobApplication;
import org.springframework.stereotype.Component;

@Component
public class TelegramMessageBuilder {

    public String buildNewApplicationMessage(JobApplication application) {
        Job job = application.getJob();

        StringBuilder sb = new StringBuilder();
        sb.append("📥 Có đơn ứng tuyển mới").append("\n\n");

        if (job != null) {
            sb.append("🧾 Công việc: ").append(nullToDash(job.getTitle())).append("\n");
        }

        sb.append("👤 Họ tên: ").append(nullToDash(application.getFullName())).append("\n");
        sb.append("📞 Số điện thoại: ").append(nullToDash(application.getPhone())).append("\n");
        sb.append("📧 Email: ").append(nullToDash(application.getEmail())).append("\n");
        sb.append("🎂 Năm sinh: ").append(application.getBirthYear() != null ? application.getBirthYear() : "-").append("\n");
        sb.append("⚧ Giới tính: ").append(nullToDash(application.getGender())).append("\n");
        sb.append("🏫 Trường: ").append(nullToDash(application.getSchoolName())).append("\n");
        sb.append("📘 Chuyên ngành: ").append(nullToDash(application.getMajor())).append("\n");
        sb.append("🕒 Thời gian rảnh: ").append(nullToDash(application.getAvailableTime())).append("\n");
        sb.append("💼 Kinh nghiệm: ").append(nullToDash(application.getExperienceText())).append("\n");
        sb.append("📝 Ghi chú: ").append(nullToDash(application.getNote())).append("\n");
        sb.append("📎 CV: ").append(nullToDash(application.getCvFileUrl())).append("\n");
        sb.append("📌 Trạng thái: ").append(nullToDash(application.getStatus()));

        return sb.toString();
    }

    private String nullToDash(String value) {
        return value == null || value.isBlank() ? "-" : value;
    }
}