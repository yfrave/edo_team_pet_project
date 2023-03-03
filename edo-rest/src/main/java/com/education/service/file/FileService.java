package com.education.service.file;


import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    void uploadFile(MultipartFile multipartFile);
}
