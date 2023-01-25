package com.education.service.filepool;


import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;

import java.util.List;

public interface FilePoolService {
    FilePoolDto add(FilePoolDto filePool);

    FilePoolDto findById(Long id);

    List<FilePool> findAllById(List<Long> id);

    void moveToArchive(Long id);
}
