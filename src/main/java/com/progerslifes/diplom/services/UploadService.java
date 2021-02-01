package com.progerslifes.diplom.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    void saveFile(String uploadDir, String fileName,
                  MultipartFile multipartFile) throws IOException;
    String getPath(String uploadDir, String fileName) throws IOException;
}
