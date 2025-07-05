package com.manager.account.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import java.util.Set;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final String baseUrl;

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/webp");

    public FileStorageService(
            @Value("${file.upload-dir}") String uploadDir,
            @Value("${file.base-url}") String baseUrl
    ) throws IOException {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
        this.baseUrl = baseUrl;
    }

    public String storeFile(MultipartFile file) throws IOException {
        // Validate loại file
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IOException("File không hợp lệ. Chỉ chấp nhận jpg, png, webp.");
        }

        // Sinh tên file random
        String originalExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "." + originalExtension;

        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
}
