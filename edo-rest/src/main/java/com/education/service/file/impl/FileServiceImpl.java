package com.education.service.file.impl;

import com.education.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Дарья Лукьянова
 * Сервис-класс для добавления файла в файловое хранилище (FilePool)
 */

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String location = "C:/Users/Dasha/Desktop/test";
        File file = new File(location);
        if (!file.exists()) {
            file.mkdir();
        }

        file = new File(location + "/" + fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
