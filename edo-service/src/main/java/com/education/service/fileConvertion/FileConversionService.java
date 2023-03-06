package com.education.service.fileConvertion;

import org.jvnet.hk2.annotations.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileConversionService {

    void convertFile(MultipartFile multipartFile);
}
