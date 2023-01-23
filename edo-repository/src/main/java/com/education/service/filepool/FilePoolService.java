package com.education.service.filepool;

import com.education.entity.FilePool;

import java.util.List;

public interface FilePoolService {
    FilePool add(FilePool filePool);

    FilePool findById(Long id);

    List<FilePool> findAllById(List<Long> id);

    void moveToArchive(Long id);
}
