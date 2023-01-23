package com.education.service.filepool.impl;

import com.education.entity.FilePool;
import com.education.repository.FilePoolRepository;
import com.education.service.filepool.FilePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilePoolServiceImpl implements FilePoolService {
    private final FilePoolRepository filePoolRepository;

    @Autowired
    public FilePoolServiceImpl(FilePoolRepository filePoolRepository) {
        this.filePoolRepository = filePoolRepository;
    }

    public FilePool add(FilePool filePool) {
        filePoolRepository.save(filePool);
        return filePool;
    }

    public FilePool findById(Long id) {
        return filePoolRepository.findById(id)
                .get();
    }

    public List<FilePool> findAllById(List<Long> ids) {
        return filePoolRepository.findAllById(ids);
    }

    public void moveToArchive(Long id) {
        filePoolRepository.moveToArchive(id);
    }


}
