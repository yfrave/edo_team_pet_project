package com.education.service.filepool.impl;

import com.education.entity.FilePool;
import com.education.model.dto.FilePoolDto;
import com.education.repository.FilePoolRepository;
import com.education.service.filepool.FilePoolService;
import com.education.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilePoolServiceImpl implements FilePoolService {
    private final FilePoolRepository filePoolRepository;

    @Autowired
    public FilePoolServiceImpl(FilePoolRepository filePoolRepository) {
        this.filePoolRepository = filePoolRepository;
    }
    @Transactional(rollbackFor = Exception.class)
    public FilePoolDto add(FilePoolDto filePool) {
        filePoolRepository.save(DtoConverter.convertFromDto(filePool));
        return filePool;
    }
    @Transactional(rollbackFor = Exception.class)
    public FilePoolDto findById(Long id) {
        FilePool filePool = filePoolRepository.findById(id).orElse(null);
        return filePool != null ? DtoConverter.convertToDto(filePool) : null;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<FilePool> findAllById(List<Long> ids) {
        return filePoolRepository.findAllById(ids);
    }
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        filePoolRepository.moveToArchive(id);
    }


}
