package com.epu.partimeconnect.service;

import com.epu.partimeconnect.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${app.upload-dir}")
    private String uploadDir;

    public String store(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            Path root = Paths.get(uploadDir, folder).toAbsolutePath().normalize();
            Files.createDirectories(root);
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID() + (ext != null ? "." + ext : "");
            Files.copy(file.getInputStream(), root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + folder + "/" + fileName;
        } catch (IOException e) {
            throw new BadRequestException("Upload file failed");
        }
    }

    public void deleteByUrl(String fileUrl) {
        if (fileUrl == null || fileUrl.isBlank() || !fileUrl.startsWith("/uploads/")) {
            return;
        }
        try {
            String relative = fileUrl.replaceFirst("/uploads/", "");
            Path path = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(relative);
            Files.deleteIfExists(path);
        } catch (IOException ignored) {
        }
    }
}
