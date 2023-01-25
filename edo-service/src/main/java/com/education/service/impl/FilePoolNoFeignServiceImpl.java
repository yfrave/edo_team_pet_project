package com.education.service.impl;

import com.education.model.dto.FilePoolDto;
import com.education.service.FilePoolNoFeignService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FilePoolNoFeignServiceImpl implements FilePoolNoFeignService {
    private final String URL = "http://edo-repository/api/repository/address/";

    private final RestTemplate restTemplate;

    public FilePoolDto findById(Long id) {
        return restTemplate.getForObject(URL + id, FilePoolDto.class);
    }

    public FilePoolDto add(FilePoolDto filePoolDto) {
        HttpEntity<FilePoolDto> request = new HttpEntity<FilePoolDto>(filePoolDto);
        return restTemplate.postForObject(URL,request, FilePoolDto.class);

    }

    public List<FilePoolDto> findAllById(List<Long> ids) {
        List<FilePoolDto> filePoolDtos = new ArrayList<>();
        return filePoolDtos;
    }

    public void moveToArchive(Long id) {

    }

}
