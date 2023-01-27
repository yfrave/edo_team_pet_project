package com.education.service;

import com.education.model.dto.FilePoolDto;

import java.util.List;

public interface FilePoolService {

    FilePoolDto add(FilePoolDto filePoolDto);

    FilePoolDto findById(Long id);

    List<FilePoolDto> findAllById(List<Long> ids);

    void moveToArchive(Long id);

}
